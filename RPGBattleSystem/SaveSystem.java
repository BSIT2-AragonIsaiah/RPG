/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Isaiah Aragon
 */
public class SaveSystem {

    private static final String SAVE_FILE = "save.txt";

    // SAVE 
    public static void save(StageManager stageManager, BattleSystem battle) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {

            // STAGE + GOLD
            writer.println(stageManager.getStage());
            writer.println(stageManager.getGold());

            // ================= PARTY =================
            ArrayList<Character> party = battle.getParty();
            writer.println(party.size());

            for (Character c : party) {
                writer.println(c.getClass().getSimpleName());
                writer.println(c.getName());
                writer.println(c.getHp());
                writer.println(c.getMp());
            }

            // ================= ENEMIES =================
            ArrayList<Character> enemies = battle.getEnemies();
            writer.println(enemies.size());

            for (Character e : enemies) {
                writer.println(e.getClass().getSimpleName());
                writer.println(e.getName());
                writer.println(e.getHp());
            }

            // ================= INVENTORY =================
            ArrayList<Items> inventory = stageManager.getInventory();
            writer.println(inventory.size());

            for (Items item : inventory) {
                writer.println(item.getName());
            }

            JOptionPane.showMessageDialog(null, "Game Saved!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // LOAD 
    public static LoadedData load() {

        File file = new File(SAVE_FILE);

        if (!file.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            // STAGE + GOLD
            int stage = Integer.parseInt(reader.readLine());
            int gold = Integer.parseInt(reader.readLine());

            // ================= PARTY =================
            int partySize = Integer.parseInt(reader.readLine());
            ArrayList<Character> party = new ArrayList<>();

            for (int i = 0; i < partySize; i++) {

                String type = reader.readLine();
                String name = reader.readLine();
                int hp = Integer.parseInt(reader.readLine());
                int mp = Integer.parseInt(reader.readLine());

                Character c = createCharacter(type, name);

                c.takeTrueDamage(c.getMaxHp() - hp);
                c.useMana(c.getMaxMp() - mp);

                party.add(c);
            }

            // ================= ENEMIES =================
            int enemySize = Integer.parseInt(reader.readLine());
            ArrayList<Character> enemies = new ArrayList<>();

            for (int i = 0; i < enemySize; i++) {

                String type = reader.readLine();
                String name = reader.readLine();
                int hp = Integer.parseInt(reader.readLine());

                Character e = createCharacter(type, name);
                e.takeTrueDamage(e.getMaxHp() - hp);

                enemies.add(e);
            }

            // ================= INVENTORY =================
            int invSize = Integer.parseInt(reader.readLine());
            ArrayList<Items> inventory = new ArrayList<>();

            for (int i = 0; i < invSize; i++) {
                String itemType = reader.readLine();
                inventory.add(new Items(itemType));
            }

            return new LoadedData(stage, gold, party, enemies, inventory);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // FOR CHARACTER FACTORY
    private static Character createCharacter(String type, String name) {

        return switch (type) {
            case "Warrior" -> new Warrior(name);
            case "Mage" -> new Mage(name);
            case "Archer" -> new Archer(name);
            case "Thief" -> new Thief(name);
            case "Bulwark" -> new Bulwark(name);
            default -> new Warrior(name);
        };
    }

    // DATA HOLDER 
    public static class LoadedData {

        public int stage;
        public int gold;
        public ArrayList<Character> party;
        public ArrayList<Character> enemies;
        public ArrayList<Items> inventory;

        public LoadedData(int stage, int gold,
                          ArrayList<Character> party,
                          ArrayList<Character> enemies,
                          ArrayList<Items> inventory) {

            this.stage = stage;
            this.gold = gold;
            this.party = party;
            this.enemies = enemies;
            this.inventory = inventory;
        }
    }
    
    
    // THESE IS TO AVOID ENDING BUG
    public static void deleteSave() {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            file.delete();
            JOptionPane.showMessageDialog(null, "Save deleted.");
        }
    }
}
