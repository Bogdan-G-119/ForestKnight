package org.example;

import java.awt.*;
import java.awt.Rectangle;

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

    public void draw(Graphics g){
        g.drawRect(x, y, width, height);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
}
