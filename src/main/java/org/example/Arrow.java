package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Arrow {
    double x, y;
    double dx, dy;

    int size = 5;
    int speed = 10;
    boolean isAlive = true;

    public Arrow(int startX, int startY, int targetX, int targetY){
        this.x = startX;
        this.y = startY;

        double dirX = targetX - startX;
        double dirY = targetY - startY;

        double length = Math.sqrt(dirX * dirX + dirY * dirY);
        if(length == 0) length = 1;

        dx = (dirX / length) * speed;
        dy = (dirY / length) * speed;
    }

    public void update(){
        x += dx;
        y += dy;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect((int)x, (int)y, size, size);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, size, size);
    }
}
