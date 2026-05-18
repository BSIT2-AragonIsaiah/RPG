/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Isaiah Aragon
 */
public class SaveData implements Serializable {

    private int stage;
    private int gold;
    private ArrayList<Character> party;
    private ArrayList<Items> inventory;

    public SaveData(int stage, int gold,
                    ArrayList<Character> party,
                    ArrayList<Items> inventory) {

        this.stage = stage;
        this.gold = gold;
        this.party = party;
        this.inventory = inventory;
    }

    public int getStage() { return stage; }
    public int getGold() { return gold; }
    public ArrayList<Character> getParty() { return party; }
    public ArrayList<Items> getInventory() { return inventory; }
}
