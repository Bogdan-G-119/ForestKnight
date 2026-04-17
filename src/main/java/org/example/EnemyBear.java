package org.example;

import javax.swing.*;
import java.awt.*;

public class EnemyBear extends Enemy{
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

        g2.rotate(-angleToPlayer, centerX, centerY);
        if(hitFlashTime > 0 && hitFlashTime % 2 == 0){
            g.setColor(Color.RED);
        } else {
            g.setColor(new Color(165, 42, 42));
        }
    }

    public EnemyBear(int x, int y) {
        this.x = x;
        this.y = y;
        width = 30;
        height = 30;
        drawWidth = width*10;
        drawHeight = height*10;
        speed = 2;
        damage = 4;
        scoreValue = 60;
        hp = 35;
        isAlive = true;
        knockBackResistance = 0.5F;
        image = new ImageIcon(getClass().getResource("/Bear.png")).getImage();
    }
}
