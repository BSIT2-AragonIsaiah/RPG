/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Isaiah Aragon
 */
public class RPG_Main {

    public static void main(String[] args) {

        
        try {
            Class.forName("RPGBattleSystem.Warrior");
            Class.forName("RPGBattleSystem.Archer");
            Class.forName("RPGBattleSystem.Mage");
            Class.forName("RPGBattleSystem.Thief");
            Class.forName("RPGBattleSystem.Bulwark");
            Class.forName("RPGBattleSystem.Normie");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SaveSystem.LoadedData data = SaveSystem.load();

        // SAFETY CHECK (prevents loading finished game)
        if (data != null && data.stage > 6) {
            SaveSystem.deleteSave();
            data = null;
        }

        ArrayList<Character> party;
        StageManager stageManager;
        BattleSystem battle;

        if (data != null) {
            //  LOAD GAME 
            party = data.party;

            stageManager = new StageManager(party);
            stageManager.setStage(data.stage);
            stageManager.setGold(data.gold);
            
            //HARD RESET
            for (Character c : party) {
                c.heal(9999);
                c.restoreMana(9999);
            }
            // LOAD INVENTORY ONCE
            stageManager.getInventory().addAll(data.inventory);

            battle = stageManager.startNextBattle();

        } else {
            // NEW GAME
            party = new ArrayList<>();

            for (int i = 0; i < 3; i++) {

                String[] options = CharacterFactory.getAvailableCharacters();

                try {
                    int choice = JOptionPane.showOptionDialog(
                        null,
                        "Choose character " + (i + 1),
                        "Party Selection",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                    );

                    if (choice < 0) {
                        throw new IllegalArgumentException("Player cancelled selection.");
                    }

                    party.add(CharacterFactory.create(options[choice], options[choice]));

                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Game closed.");
                    System.exit(0);
                }
            }

            party = renameDuplicates(party);
            stageManager = new StageManager(party);

            battle = stageManager.startNextBattle();
        }

        new RPG_screen(battle, stageManager).setVisible(true);
    }

    private static ArrayList<Character> renameDuplicates(ArrayList<Character> party) {

        java.util.HashMap<String, Integer> count = new java.util.HashMap<>();

        for (Character c : party) {
            String base = c.getName();

            count.put(base, count.getOrDefault(base, 0) + 1);
            int num = count.get(base);

            if (num > 1) {
                try {
                    java.lang.reflect.Field nameField = Character.class.getDeclaredField("name");
                    nameField.setAccessible(true);
                    nameField.set(c, base + num);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return party;
    }
}
