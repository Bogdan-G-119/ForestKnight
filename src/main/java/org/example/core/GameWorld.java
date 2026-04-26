package org.example.core;

import org.example.Drawable;
import org.example.entities.Arrow;
import org.example.entities.Enemy;
import org.example.entities.Player;
import org.example.input.InputState;
import org.example.items.PowerUp;
import org.example.weapons.Weapon;
import org.example.weapons.WeaponCrossbow;
import org.example.weapons.WeaponSword;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Riadi celú hernú logiku.
 * Obsahuje hráča, nepriateľov, projektily a power-upy
 * a zabezpečuje ich aktualizáciu a renderovanie.
 */
public class GameWorld {

    public Player player;
    public ArrayList<Enemy> enemies;
    public ArrayList<PowerUp> powerUps;
    public ArrayList<Arrow> arrows;
    public WaveManager waveManager;

    public final Weapon sword = new WeaponSword();
    public final Weapon crossbow = new WeaponCrossbow();
    Image background, crossbowImg, swordImg;
    //List<Updatable> updatables;
    //List<Drawable> drawables;
    GameContext context;

    public void render(Graphics g, int width, int height) {
        drawBG(g, width, height);

        player.draw(g);

        for (Drawable d : enemies) d.draw(g);
        for (Drawable d : powerUps) d.draw(g);
        for (Drawable d : arrows) d.draw(g);

        drawUI(g, width, height);
    }

    public GameWorld(Image background, Image swordImg, Image crossbowImg) {
        this.background = background;
        this.swordImg = swordImg;
        this.crossbowImg = crossbowImg;
        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        arrows = new ArrayList<>();

        player = new Player();
        player.setWeapon(sword, swordImg);
        waveManager = new WaveManager(enemies, powerUps);
        context = new GameContext(player, enemies, arrows, powerUps);
    }

    public void update(InputState input, int width, int height) {

        if (!player.isAlive()) return;

        waveManager.update();

        context.player.update(input, width, height, context);

        if (input.key1) {
            player.setWeapon(sword, swordImg);
        }

        if (input.key2) {
            player.setWeapon(crossbow, crossbowImg);
        }

        input.key1 = false;
        input.key2 = false;

        updatePowerUps();
        updateEnemies();
        updateArrows();
    }


    public void updatePowerUps(){
        for(PowerUp power : powerUps){
            power.update(context);
        }
    }

    public void updateEnemies(){
        Iterator<Enemy> iterator = context.enemies.iterator();

        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update(context);

            if (collisionEnable(enemy)) {
                context.player.handleCollisionWithEnemy(enemy);
            }

            if(enemy.isDead() && enemy.isReadyToRemove()){
                iterator.remove();
            }
        }
    }
    public void updateArrows(){
        arrows.removeIf(arrow -> arrow.update(context));
    }

    public void drawUI(Graphics g, int width, int height){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(10, 10, 200, 80);

        g.setColor(Color.WHITE);
        g.drawString("HP: " + player.getHP(), 20, 30);
        g.drawString("Arrows: " + player.getArrowsLeft(), 20, 50);
        g.drawString("Score: " + player.getScore(), 70, 30);
        g.drawString("Wave: " + waveManager.waveNumber, 100, 50);

        g.drawRect(10, height - 60, 100, 50);
        g.drawString("1: Sword", 15, height - 40);
        g.drawString("2: Bow", 15, height - 20);
    }

    public void drawBG(Graphics g, int width, int height){
        g.drawImage(background, 0, 0, width, height, null);
    }


    private boolean collisionEnable(Enemy enemy) {
        return player.getBounds().intersects(enemy.getBounds());
    }
}