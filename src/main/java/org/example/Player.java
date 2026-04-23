package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Objects;

public class Player {
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

    int damageCoolDown = 0;
    int attackCoolDown = 0;

    int damage = 3;
    int extraDamage = 0;
    int attackSize = 30;

    Weapon currentWeapon;
    Weapon sword = new WeaponSword();
    Weapon crossbow;
    int arrowsLeft = 20;
    double playerAngle;
    Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Player.png"))).getImage();
    public int getX() { return x; }
    public int getY() { return y; }
    public int getHP() { return hp; }
    public int getSpeed() { return speed; }
    public boolean isAlive() { return isAlive; }

    public Player() {currentWeapon = sword;}
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        currentWeapon = sword;
    }
    double angle(int mouseX, int mouseY){
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

    public void updateEffects() {
        for (int i = activeEffects.size() - 1; i >= 0; i--) {
            activeEffects.get(i).update(this);
        }
    }

    /*public void tick(Player player, ArrayList<Enemy> enemies, int mouseX, int mouseY){
        currentWeapon.attack(player, enemies, mouseX, mouseY);

        updateEffects();
    }*/

    public void draw(Graphics g, int mouseX, int mouseY){
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

    public void attack(ArrayList<Enemy> enemies, int mouseX, int mouseY, boolean mousePressed){
        currentWeapon.attack(this, enemies, mouseX, mouseY, mousePressed);
    }

    public Rectangle getAttackBounds(int mouseX, int mouseY, int attackSize){
        AttackDirection dir = getAttackDirection(mouseX, mouseY);
        switch (dir){
            case UP: return new Rectangle(x, y-attackSize-height, attackSize, attackSize);
            case DOWN: return new Rectangle(x, y+2*height, attackSize, attackSize);
            case LEFT: return new Rectangle(x-attackSize-width, y, attackSize, attackSize);
            case RIGHT: return new Rectangle(x+2*width, y, attackSize, attackSize);
        }
        return new Rectangle(x, y, width, height);
    }

    public enum AttackDirection {
        UP, DOWN, LEFT, RIGHT
    }

    public AttackDirection getAttackDirection(int mouseX, int mouseY){
        int dx = mouseX - (x + width/2);
        int dy = mouseY - (y + height/2);

        if(Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? AttackDirection.RIGHT : AttackDirection.LEFT;
        } else {
            return dy > 0 ? AttackDirection.DOWN : AttackDirection.UP;
        }
    }

    public void update(int mouseX, int mouseY, boolean mouseClicked,
                       boolean up, boolean down, boolean left, boolean right,
                       int width, int height, ArrayList<Enemy> enemies) {

        playerAngle = angle(mouseX, mouseY);

        if (arrowsLeft > 30) arrowsLeft = 30;

        attack(enemies, mouseX, mouseY, mouseClicked);

        if (damageCoolDown > 0) damageCoolDown--;

        if (hp <= 0) isAlive = false;

        move(up, down, left, right, width, height);
    }

    public void move(boolean up, boolean down, boolean left, boolean right, int width, int height) {
        if (up && y > 0) y -= speed;
        if (down && y < height - this.height) y += speed;
        if (left && x > 0) x -= speed;
        if (right && x < width - this.width) x += speed;
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

    private boolean collisionEnable(Enemy enemy, Rectangle attackRect) {
        return enemy.getBounds().intersects(attackRect);
    }

    public float getAttackProgress(){
        return 1f - (attackCoolDown/30f);
    }
}
