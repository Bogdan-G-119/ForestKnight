package org.example.entities;

import org.example.Drawable;
import org.example.core.GameContext;
import org.example.entities.Enemy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Projektil vystrelený z kuše.
 * Pohybuje sa smerom k cieľu a poškodzuje nepriateľov pri zásahu.
 */
public class Arrow implements Drawable {
    double x, y;
    double dx, dy;

    int size = 5;
    int drawWidth = size*10;
    int drawHeight = size*10;
    int speed = 10;
    int damage = 4;
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

    public boolean update(GameContext context){
        x += dx;
        y += dy;
        int sourceX = (int)(x - dx);
        int sourceY = (int)(y - dy);
        for (Enemy enemy : context.enemies) {
            if (getBounds().intersects(enemy.getBounds())) {
                double distanceToEnemy = Math.sqrt(Math.pow((enemy.x - x), 2) + Math.pow((enemy.y - y), 2));
                boolean longRangeHit = (int)distanceToEnemy > 100;
                enemy.takeDamage(damage, longRangeHit);
                enemy.applyKnockBack(sourceX, sourceY,10);
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
