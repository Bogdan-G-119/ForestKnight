package org.example;

import java.awt.*;


public class Wolf extends Enemy{

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
    }

    @Override
    public void draw(Graphics g) {
        if(hitFlashTime > 0 && hitFlashTime % 2 == 0){
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GRAY);
        }
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }

    public Wolf(int x, int y) {
        this.x = x;
        this.y = y;
        width = 20;
        height = 20;
        speed = 3;
        damage = 2;
        scoreValue = 30;
        hp = 20;
        isAlive = true;
        knockBackResistance = 1.0F;
    }
}
