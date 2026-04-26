package org.example.entities;

import org.example.Drawable;
import org.example.core.GameContext;
import org.example.weapons.Weapon;
import org.example.weapons.WeaponSword;
import org.example.input.InputState;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Reprezentuje hráča v hre.
 * Zodpovedá za pohyb, boj, používanie zbraní a interakciu s objektmi.
 */
public class Player implements Drawable {
    ArrayList<TemporaryEffect> activeEffects = new ArrayList<>();
    private int x = 0;
    private int y = 0;

    int width = 20;
    int height = 20;

    int drawWidth = width*3;
    int drawHeight = height*3;
    private int hp = 10;
    private int speed = 6;

    int score = 0;

    boolean isAlive = true;

    private int damageCoolDown = 0;
    private int attackCoolDown = 0;

    private int damage = 3;
    int extraDamage = 0;
    private int attackSize = 50;
    int mouseX, mouseY;
    private Weapon currentWeapon;
    private int arrowsLeft = 20;
    double playerAngle;
    Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Player.png"))).getImage();
    public int getX() { return x; }
    public int getY() { return y; }
    public int getHP() { return hp; }
    public int getDamage() { return damage; }
    public int getSpeed() { return speed; }
    public boolean isAlive() { return isAlive; }
    public int getArrowsLeft() { return arrowsLeft; }
    public int getAttackSize(){return attackSize;}
    public int getAttackCoolDown() {
        return attackCoolDown;
    }
    public int getScore() {
        return score;
    }
    public Player() {}
    public Player(Weapon weapon) {currentWeapon = weapon;}
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double angle(int mouseX, int mouseY){
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        return Math.atan2(mouseY - y, mouseX - x) - Math.PI / Math.sqrt(2);
    }


    public interface TemporaryEffect {
        void update(Player player);
    }
    public void takeDamage(int damage){
        hp -= damage;
    }

    public void addTemporaryEffect(TemporaryEffect effect){
        activeEffects.add(effect);
    }

    public void removeTemporaryEffect(TemporaryEffect effect){
        activeEffects.remove(effect);
    }

    public boolean canShoot() {
        return attackCoolDown == 0 && arrowsLeft > 0;
    }

    public void onShoot() {
        attackCoolDown = 6;
        arrowsLeft--;
    }

    public boolean canHit() {
        return attackCoolDown == 0;
    }
    public void onHit() {
        attackCoolDown = 30;
    }
    public void updateEffects() {
        for (int i = activeEffects.size() - 1; i >= 0; i--) {
            activeEffects.get(i).update(this);
        }
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        int centerX = x + width / 2;
        int centerY = y + height / 2;

        g2.rotate(playerAngle, centerX, centerY);

        g2.drawImage(image, x - (drawWidth - width)/2, y - (drawHeight - height)/2, drawWidth, drawHeight, null);

        g2.rotate(-playerAngle, centerX, centerY);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void attack(GameContext context, int mouseX, int mouseY, boolean mousePressed){
        currentWeapon.attack(this, context, mouseX, mouseY, mousePressed);
    }

    public Rectangle getAttackBounds(int mouseX, int mouseY, int attackSize) {

        double angle = angle(mouseX, mouseY) + Math.PI/Math.sqrt(2);

        int centerX = x + width / 2;
        int centerY = y + height / 2;

        int range = attackSize / 2;

        int hitX = (int)(centerX + Math.cos(angle) * range);
        int hitY = (int)(centerY + Math.sin(angle) * range);

        return new Rectangle(hitX - attackSize / 2, hitY - attackSize / 2, attackSize, attackSize);
    }

    public void update(InputState input, int width, int height, GameContext context) {

        playerAngle = angle(input.mouseX, input.mouseY);

        if (arrowsLeft > 30) arrowsLeft = 30;

        attack(context, input.mouseX, input.mouseY, input.mouseClicked);

        if (damageCoolDown > 0) damageCoolDown--;

        if (attackCoolDown > 0) attackCoolDown--;

        if (hp <= 0) isAlive = false;

        move(input, width, height);

        updateEffects();
    }

    public void move(InputState input, int width, int height) {
        if (input.up && y > 0) y -= speed;
        if (input.down && y < height - this.height) y += speed;
        if (input.left && x > 0) x -= speed;
        if (input.right && x < width - this.width) x += speed;
    }
    public void addEnemyKill(int scoreValue){
        score += scoreValue;
        arrowsLeft += 5;
    }
    public void setWeapon(Weapon weapon, Image image) {
        this.currentWeapon = weapon;
        this.image = image;
    }

    public void handleCollisionWithEnemy(Enemy enemy) {
        if (damageCoolDown == 0) {
            enemy.causeDamage(this);
            damageCoolDown = 30;
        }
    }

    public void heal(int amount){
        hp += amount;
    }

    public void speedUp(int amount){
        speed += amount;
    }

    public void increaseHitBox(int amount){
        attackSize += amount;
    }

    public void increaseDamage(int amount){
        damage += amount;
    }
}
