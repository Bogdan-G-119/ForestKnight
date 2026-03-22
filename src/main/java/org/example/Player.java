package org.example;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {
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

    int damage = 5;

    public void draw(Graphics g, int mouseX, int mouseY){
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);

        Rectangle attackRect = getAttackBounds(mouseX, mouseY);

        if(attackCoolDown == 0){
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.GRAY);
        }

        g.fillRect(attackRect.x, attackRect.y, attackRect.width, attackRect.height);
        g.drawRect(x, y, width, height);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public void attack(ArrayList<Enemy> enemies, int mouseX, int mouseY){
        Rectangle attackRect = getAttackBounds(mouseX, mouseY);
        if(attackCoolDown > 0) attackCoolDown--;
        for(Enemy enemy : enemies){
            if(collisionEnable(enemy, attackRect) && attackCoolDown == 0){
                enemy.hp -= damage;
                attackCoolDown = 30;
            }
        }
    }

    public Rectangle getAttackBounds(int mouseX, int mouseY){
        AttackDirection dir = getAttackDirection(mouseX, mouseY);
        int attackSize = 20;
        switch (dir){
            case UP: return new Rectangle(x, y-attackSize-height, width, attackSize);
            case DOWN: return new Rectangle(x, y+attackSize, width, attackSize);
            case LEFT: return new Rectangle(x-attackSize-width, y, attackSize, height);
            case RIGHT: return new Rectangle(x+attackSize, y, attackSize, height);
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
}
