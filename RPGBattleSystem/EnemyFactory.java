/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RPGBattleSystem;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Isaiah Aragon
 */
public class EnemyFactory {

    private static final Random rand = new Random();

    public static ArrayList<Character> generateEnemies(int stage) {
        ArrayList<Character> enemies = new ArrayList<>();

        // FINAL BOSS STAGE
        if (stage == 6) {
            enemies.add(createBoss(stage));
            return enemies;
        }

        // NORMAL STAGES
        int enemyCount = 1 + rand.nextInt(2); // GAME CHECK

        if (stage >= 3) {
            enemyCount++; // CHALLENGE
        }

        for (int i = 0; i < enemyCount; i++) {
            enemies.add(createRandomEnemy(stage));
        }

        return enemies;
    }

    private static Character createRandomEnemy(int stage) {
        int type = rand.nextInt(4);

        Character enemy;

        switch (type) {
            case 0 -> enemy = new Warrior("Enemy Warrior");
            case 1 -> enemy = new Mage("Enemy Mage");
            case 2 -> enemy = new Archer("Enemy Archer");
            case 3 -> enemy = new Thief("Enemy Thief");
            default -> enemy = new Warrior("Enemy");
        }

        scaleEnemy(enemy, stage);
        return enemy;
    }

    private static Character createBoss(int stage) {
        Character boss = new Bulwark("FINAL BOSS");

        scaleEnemy(boss, stage);

        // Extra boss buffs
        boss.increaseAttack(10);
        boss.increaseDefense(10);
        boss.restoreMana(999);

        return boss;
    }

    private static void scaleEnemy(Character enemy, int stage) {

        // Increase stats per stage
        for (int i = 1; i < stage; i++) {
            enemy.increaseAttack(2);
            enemy.increaseDefense(1);
            enemy.restoreMana(5);
            enemy.heal(5);
        }
    }
}
