/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

/**
 *
 * @author Isaiah Aragon
 */
public class Bulwark extends Character{
    public Bulwark(String name, int hp, int maxHp, int attack, int defense, int mp, int maxMp) {
        super(name, 40, 40, 1, 10, 50, 50);
    }
    public int defenseUp()
    {
        int tempDefense = getDefense() * 2;
        return tempDefense;
    }
    @Override
    public void attack(Character enemy) {

        enemy.takeDamage(getAttack());

    }

    @Override ///COUNTER (DEF*2 ==> Attack)
    public void useSkill(Character enemy) {

        if(getMp() <= 30) {

            int counter = getDefense() * 2;

            enemy.takeDamage(counter);

            setMp(getMp() - 30);
        }
    }
    
    //Passive - Boost defense if health is below 25%
    @Override
    public void passiveSkill(Character player){
        player.takeDamage();
    }
}
