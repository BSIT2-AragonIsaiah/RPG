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
public class Items {

    private String name;

    public Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void use(Character target) {

        switch (name) {
            case "Heal Potion" -> target.heal(30);
            case "Mana Potion" -> target.restoreMana(20);
        }
    }
}
