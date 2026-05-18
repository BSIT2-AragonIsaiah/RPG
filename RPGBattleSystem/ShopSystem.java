/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;
import javax.swing.JOptionPane;
/**
 *
 * @author Isaiah Aragon
 */
public class ShopSystem {

    public static void openShop(StageManager stageManager, BattleSystem battle) {

        String[] options = {
            "Heal Potion (15g)",
            "Mana Potion (10g)",
            "Exit Shop"
        };

        while (true) {

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Gold: " + stageManager.getGold() + "\nChoose item:",
                    "Shop",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == 0) {
                if (stageManager.getGold() >= 15) {

                    boolean added = battle.addItem(new Items("Heal Potion"));

                    if (added) {
                        stageManager.spendGold(15);
                        JOptionPane.showMessageDialog(null, "Bought Heal Potion!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Inventory full!");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Not enough gold!");
                }

            } else if (choice == 1) {
                if (stageManager.getGold() >= 10) {

                    boolean added = battle.addItem(new Items("Mana Potion"));

                    if (added) {
                        stageManager.spendGold(10);
                        JOptionPane.showMessageDialog(null, "Bought Mana Potion!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Inventory full!");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Not enough gold!");
                }

            } else {
                break; // exit shop
            }
        }
    }
}
