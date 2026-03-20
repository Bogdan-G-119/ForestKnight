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


    public abstract void update(Player player);

    public abstract void draw(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public void takeDamage(Player player){
        player.hp -= damage;
    }
}
