package org.example;

import javax.swing.*;
import java.awt.*;


public class EnemyWolf extends Enemy{

    @Override
    public void update(Player player) {
        if(hitFlashTime > 0) hitFlashTime--;
        if(knockBackTime > 0){
            x += (int)knockBackX;
            y += (int)knockBackY;

            knockBackX *= 0.9;
            knockBackY *= 0.9;

            knockBackTime--;
            return;
        }
        if(player.x > x){x += speed;}
        if(player.y > y){y += speed;}
        if(player.x < x){x -= speed;}
        if(player.y < y){y -= speed;}
        angleToPlayer = angle(player);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int centerX = x + width / 2;
        int centerY = y + height / 2;

        g2.rotate(angleToPlayer, centerX, centerY);

        g2.drawImage(image, x - (drawWidth - width)/2, y - (drawHeight - height)/2, drawWidth, drawHeight, null);

        if(hitFlashTime > 0 && hitFlashTime % 2 == 0){
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GRAY);
        }
        g2.rotate(-angleToPlayer, centerX, centerY);
    }

    public EnemyWolf(int x, int y) {
        this.x = x;
        this.y = y;
        width = 20;
        height = 20;
        drawWidth = width*10;
        drawHeight = height*10;
        speed = 3;
        damage = 2;
        scoreValue = 30;
        hp = 20;
        isAlive = true;
        knockBackResistance = 1.0F;
        image = new ImageIcon(getClass().getResource("/Wolf.png")).getImage();
    }
}
