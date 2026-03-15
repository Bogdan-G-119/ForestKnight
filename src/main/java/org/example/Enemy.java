package org.example;

import java.awt.*;

public abstract class Enemy {
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;

    protected int speed = 2;
    protected int hp = 0;

    protected boolean isAlive;

    public abstract void update(Player player);

    public abstract void draw(Graphics g);
}
