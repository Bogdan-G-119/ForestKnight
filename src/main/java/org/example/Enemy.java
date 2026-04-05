package org.example;

import java.awt.*;
import java.awt.Rectangle;

public abstract class Enemy {
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;
    protected int speed = 2;
    protected int hp = 0;
    protected int damage;
    protected boolean isAlive;
    protected int scoreValue;
    double knockBackX = 0;
    double knockBackY = 0;
    int knockBackTime = 0;
    float knockBackResistance;
    int hitFlashTime = 0;

    public abstract void update(Player player);

    public abstract void draw(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public void takeDamage(Player player){
        player.hp -= damage;
    }
    public void applyKnockBack(int playerX, int playerY, double force){
        double dx = x - playerX;
        double dy = y - playerY;

        double length = Math.sqrt(dx * dx + dy * dy);
        if(length == 0) length = 1;

        knockBackX = (dx / length) * force * knockBackResistance;
        knockBackY = (dy / length) * force * knockBackResistance;

        knockBackTime = 10;
    }
}
