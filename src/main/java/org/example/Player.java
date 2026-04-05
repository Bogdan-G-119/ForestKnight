package org.example;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {
    ArrayList<TemporaryEffect> activeEffects = new ArrayList<>();
    int x = 0;
    int y = 0;

    int width = 10;
    int height = 10;

    int hp = 10;
    int speed = 6;

    int score = 0;

    boolean isAlive = true;

    int damageCoolDown = 0;
    int attackCoolDown = 0;

    int damage = 3;
    int extraDamage = 0;
    int attackSize = 30;

    public interface TemporaryEffect {
        void update(Player player);
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

    public void tick(ArrayList<Enemy> enemies, int mouseX, int mouseY, boolean mousePressed){
        attack(enemies, mouseX, mouseY, mousePressed);

        updateEffects();
    }

    public void draw(Graphics g, int mouseX, int mouseY){
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);

        Rectangle attackRect = getAttackBounds(mouseX, mouseY, attackSize);
        float progress = getAttackProgress();

        int grayValue = (int)(progress * 255);
        grayValue = Math.max(50, grayValue);

        g.setColor(new Color(grayValue, grayValue, grayValue));
        g.fillRect(attackRect.x, attackRect.y, attackRect.width, attackRect.height);
        g.setColor(Color.BLACK);
        g.drawRect(attackRect.x, attackRect.y, attackRect.width, attackRect.height);
        g.drawRect(x, y, width, height);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void attack(ArrayList<Enemy> enemies, int mouseX, int mouseY, boolean mousePressed){
        attack(enemies, mouseX, mouseY, extraDamage, mousePressed);
    }

    public void attack(ArrayList<Enemy> enemies, int mouseX, int mouseY, int extraDamage, boolean mousePressed) {
        if(mousePressed && attackCoolDown == 0) {
            Rectangle attackRect = getAttackBounds(mouseX, mouseY, attackSize);
            for (Enemy enemy : enemies) {
                if (collisionEnable(enemy, attackRect)) {
                    enemy.hp -= damage + extraDamage;
                }
            }
            attackCoolDown = 30;
        }
        if (attackCoolDown > 0) attackCoolDown--;
    }

    public Rectangle getAttackBounds(int mouseX, int mouseY, int attackSize){
        AttackDirection dir = getAttackDirection(mouseX, mouseY);
        switch (dir){
            case UP: return new Rectangle(x, y-attackSize-height, width, attackSize);
            case DOWN: return new Rectangle(x, y+2*height, width, attackSize);
            case LEFT: return new Rectangle(x-attackSize-width, y, attackSize, height);
            case RIGHT: return new Rectangle(x+2*width, y, attackSize, height);
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

    private boolean collisionEnable(Enemy enemy, Rectangle attackRect) {
        return enemy.getBounds().intersects(attackRect);
    }

    public float getAttackProgress(){
        return 1f - (attackCoolDown/30f);
    }
}
