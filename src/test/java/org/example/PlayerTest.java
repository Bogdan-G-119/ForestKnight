package org.example;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    /*@Test
    void testAttackDirectionRight() {
        Player player = new Player(50, 50);

        Player.AttackDirection dir = player.getAttackDirection(100, 50);

        assertEquals(Player.AttackDirection.RIGHT, dir);
    }

    @Test
    void testAttackDirectionLeft() {
        Player player = new Player(50, 50);


        Player.AttackDirection dir = player.getAttackDirection(0, 50);

        assertEquals(Player.AttackDirection.LEFT, dir);
    }

    @Test
    void testAttackDealsDamage() {
        Player player = new Player(50, 50);

        Enemy enemy = new EnemyWolf(60, 50);
        int initialHp = enemy.hp;

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        player.attack(enemies, 100, 50, true);

        assertTrue(enemy.hp < initialHp);
    }

    @Test
    void testAttackCoolDown() {
        Player player = new Player(50, 50);

        Enemy enemy = new EnemyWolf(60, 50);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        player.attack(enemies, 100, 50, true);
        int hpAfterFirstHit = enemy.hp;

        player.attack(enemies, 100, 50, true);

        assertEquals(hpAfterFirstHit, enemy.hp);
    }*/
}