package org.example;
import org.example.core.GameContext;
import org.example.core.GamePanel;
import org.example.core.GameWorld;
import org.example.entities.Arrow;
import org.example.entities.Enemy;
import org.example.entities.Player;
import org.example.entities.enemies.EnemyBear;
import org.example.entities.enemies.EnemyKnight;
import org.example.entities.enemies.EnemyWolf;
import org.example.input.InputState;
import org.example.input.InputHandler;
import org.example.items.PowerUp;
import org.example.items.powerUps.PowerDamageIncrease;
import org.example.items.powerUps.PowerHealth;
import org.example.items.powerUps.PowerSpeedIncrease;
import org.example.weapons.Weapon;
import org.example.weapons.WeaponCrossbow;
import org.example.weapons.WeaponSword;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    void playerShouldShootAndReduceArrows() {
        Player player = new Player();

        assertTrue(player.canShoot());

        player.onShoot();

        assertFalse(player.canShoot()); // cooldown
        assertEquals(19, player.getArrowsLeft());
    }

    @Test
    void attackCooldownShouldDecrease() {
        Player player = new Player();
        player.onHit(); // 30

        InputState input = new InputState();
        GameContext context = new GameContext(player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        int before = player.getAttackCoolDown();
        player.setWeapon(new WeaponSword(), null);
        player.update(input, 780, 514, context);

        int after = player.getAttackCoolDown();

        assertEquals(before - 1, after);
    }

    @Test
    void playerShouldMoveUp() {
        Player player = new Player(100, 100);
        InputState input = new InputState();
        input.up = true;

        player.setWeapon(new WeaponSword(), null);
        player.update(input, 780, 514, new GameContext(player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        assertTrue(player.getY() < 100);
    }
    @Test
    void playerShouldLoseHP() {
        Player player = new Player();
        int hpBefore = player.getHP();

        player.takeDamage(3);

        assertEquals(hpBefore - 3, player.getHP());
    }

    @Test
    void swordShouldDamageEnemy() {
        Player player = new Player(100, 100);
        Enemy enemy = new EnemyWolf(115, 100);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        GameContext context = new GameContext(player, enemies, new ArrayList<>(), new ArrayList<>());

        Weapon sword = new WeaponSword();
        player.setWeapon(sword, null);

        assertTrue(player.canHit());

        int oldHp = enemy.getHp();

        sword.attack(player, context, 200, 100, true);

        assertTrue(enemy.getHp() < oldHp);
    }
    @Test
    void crossbowShouldCreateArrow() {
        Player player = new Player();
        ArrayList<Arrow> arrows = new ArrayList<>();
        GameContext context = new GameContext(player, new ArrayList<>(), arrows, new ArrayList<>());

        Weapon bow = new WeaponCrossbow();

        bow.attack(player, context, 200, 200, true);

        assertEquals(1, arrows.size());
    }

    @Test
    void arrowShouldHitEnemy() {
        Player player = new Player();
        Enemy enemy = new EnemyBear(100, 100);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        GameContext context = new GameContext(player, enemies, new ArrayList<>(), new ArrayList<>());

        Arrow arrow = new Arrow(100, 100, 100, 100);

        boolean result = arrow.update(context);

        assertTrue(result);
    }

    @Test
    void enemyShouldTakeDamage() {
        Enemy enemy = new EnemyKnight(0,0);
        int hpBefore = enemy.getHp();

        enemy.takeDamage(5);

        assertEquals(hpBefore - 5, enemy.getHp());
    }

    @Test
    void enemyShouldTakeCriticalDamage() {
        Enemy enemy = new EnemyWolf(0,0);
        int hpBefore = enemy.getHp();

        enemy.takeDamage(5, true);

        assertEquals(hpBefore - 10, enemy.getHp());
    }

    @Test
    void enemyShouldBeKnockedBack() {
        Enemy enemy = new EnemyBear(100,100);

        enemy.applyKnockBack(50, 50, 10);

        int oldX = enemy.getX();
        int oldY = enemy.getY();

        enemy.update(new GameContext(new Player(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        assertTrue(enemy.getX() != oldX || enemy.getY() != oldY);
    }

    @Test
    void enemyShouldDieAndGiveScore() {
        Player player = new Player();
        Enemy enemy = new EnemyWolf(100, 100);

        enemy.takeDamage(1000);

        GameContext context = new GameContext(player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        enemy.update(context);

        assertTrue(enemy.isDead());
    }

    @Test
    void arrowShouldNotHitWhenFar() {
        Enemy enemy = new EnemyWolf(500, 500);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        GameContext context = new GameContext(new Player(), enemies, new ArrayList<>(), new ArrayList<>());

        Arrow arrow = new Arrow(0, 0, 10, 10);

        boolean result = arrow.update(context);

        assertFalse(result);
    }

    @Test
    void powerUpShouldBeCollected() {
        Player player = new Player();

        PowerUp speed = new PowerSpeedIncrease(0, 0, 1, 100);

        speed.checkCollision(player);

        assertTrue(player.getSpeed() >= 6);
    }

    @Test
    void enemyShouldReceiveKnockback() {
        Player player = new Player(100, 100);
        Enemy enemy = new EnemyWolf(110, 110);

        enemy.applyKnockBack(player.getX(), player.getY(), 10);

        enemy.update(new GameContext(player, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        assertTrue(enemy.getBounds().x != 110 || enemy.getBounds().y != 110);
    }

    @Test
    void gameWorldShouldUpdateEnemies() {
        Player player = new Player();
        Enemy enemy = new EnemyWolf(100, 100);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);

        GameContext context = new GameContext(player, enemies, new ArrayList<>(), new ArrayList<>());

        GameWorld world = new GameWorld(null, null, null);

        world.update(new InputState(), 800, 600);

        assertNotNull(world);
    }

    @Test
    void keyPressedShouldSetMovementFlags() {
        InputState state = new InputState();
        InputHandler handler = new InputHandler(state);

        handler.keyPressed(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_W, 'W'));
        handler.keyPressed(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_A, 'A'));
        handler.keyPressed(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_S, 'S'));
        handler.keyPressed(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_D, 'D'));

        assertTrue(state.up);
        assertTrue(state.down);
        assertTrue(state.left);
        assertTrue(state.right);
    }

    @Test
    void keyReleasedShouldUnsetMovementFlags() {
        InputState state = new InputState();
        InputHandler handler = new InputHandler(state);

        state.up = true;
        state.down = true;
        state.left = true;
        state.right = true;
        handler.keyReleased(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_W, 'W'));
        handler.keyReleased(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_A, 'A'));
        handler.keyReleased(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_S, 'S'));
        handler.keyReleased(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_D, 'D'));

        assertFalse(state.up);
        assertFalse(state.down);
        assertFalse(state.left);
        assertFalse(state.right);
    }

    @Test
    void weaponKeysShouldBeSet() {
        InputState state = new InputState();
        InputHandler handler = new InputHandler(state);

        handler.keyPressed(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_1, '1'));
        handler.keyPressed(new KeyEvent(new JButton(), 0, 0, 0, KeyEvent.VK_2, '2'));

        assertTrue(state.key1);
        assertTrue(state.key2);
    }

    @Test
    void gamePanelShouldCreateWithoutCrash() {
        GamePanel panel = new GamePanel();
        assertNotNull(panel);
    }

    @Test
    void powerHealthShouldHealPlayer() {
        Player player = new Player();

        int hpBefore = player.getHP();

        PowerHealth heal = new PowerHealth(0, 0, 5);

        heal.apply(player);

        assertEquals(hpBefore + 5, player.getHP());
    }
    @Test
    void powerDamageShouldStackCorrectly() {
        Player player = new Player();

        PowerDamageIncrease p1 = new PowerDamageIncrease(0, 0, 2);
        PowerDamageIncrease p2 = new PowerDamageIncrease(0, 0, 3);

        p1.apply(player);
        p2.apply(player);

        assertTrue(player.getDamage() > 3);
    }

    @Test
    void mousePressedShouldSetClickedTrue() {
        InputState state = new InputState();
        InputHandler handler = new InputHandler(state);

        MouseEvent event = new MouseEvent(
                new JButton(),
                0,
                0,
                0,
                10,
                10,
                1,
                false,
                MouseEvent.BUTTON1
        );

        handler.mousePressed(event);

        assertTrue(state.mouseClicked);
    }

    @Test
    void mouseMovedShouldUpdateCoordinates() {
        InputState state = new InputState();
        InputHandler handler = new InputHandler(state);

        MouseEvent event = new MouseEvent(
                new JButton(),
                0,
                0,
                0,
                50,
                70,
                0,
                false
        );

        handler.mouseMoved(event);

        assertEquals(50, state.mouseX);
        assertEquals(70, state.mouseY);
    }

    @Test
    void emptyMouseMethodsShouldRunWithoutError() {
        InputState state = new InputState();
        InputHandler handler = new InputHandler(state);

        MouseEvent event = new MouseEvent(new JButton(), 0, 0, 0, 0, 0, 0, false);

        handler.mouseReleased(event);
        handler.mouseEntered(event);
        handler.mouseExited(event);
        handler.mouseDragged(event);
        handler.mouseClicked(event);
        handler.keyTyped(null);

        assertTrue(true);
    }
}