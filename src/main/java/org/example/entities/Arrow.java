package org.example.entities;

import org.example.entities.Enemy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Arrow {
    double x, y;
    double dx, dy;

    int size = 5;
    int drawWidth = size*10;
    int drawHeight = size*10;
    int speed = 10;
    boolean isAlive = true;
    Image image;

    double angle(){
        return Math.atan2(dy, dx) + Math.PI / 2;
    }
//    double angleToPlayer = angle();

    public Arrow(int startX, int startY, int targetX, int targetY){
        this.x = startX;
        this.y = startY;

        double dirX = targetX - startX;
        double dirY = targetY - startY;

        double length = Math.sqrt(dirX * dirX + dirY * dirY);
        if(length == 0) length = 1;

        dx = (dirX / length) * speed;
        dy = (dirY / length) * speed;
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Arrow.png"))).getImage();
        if(image == null){
            System.out.println("IMAGE NOT FOUND");
        }
    }

    public boolean update(ArrayList<Enemy> enemies){
        x += dx;
        y += dy;
        for (Enemy enemy : enemies) {
            if (getBounds().intersects(enemy.getBounds())) {
                enemy.takeDamage(5);
                enemy.applyKnockBack((int) x, (int)y, 10);
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        int centerX = (int) (x + size / 2);
        int centerY = (int) (y + size / 2);

        g2.rotate(angle(), centerX, centerY);

        g2.drawImage(image, (int) (x - (drawWidth - size)/2), (int) (y - (drawHeight - size)/2), drawWidth, drawHeight, null);

        g2.rotate(-angle(), centerX, centerY);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, size, size);
    }
}
