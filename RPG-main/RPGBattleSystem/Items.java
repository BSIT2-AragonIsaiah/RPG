/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;

/**
 *
 * @author Isaiah Aragon
 */
public class Items extends Character{
    
    public void useHealpot(Character player) {

        player.takeheal(5);
    }
    public void useUtilpot(Character player) {

        player.boost();
    }
    public void useMppot(Character player) {

        player.moveCooldwn();
    }
}
