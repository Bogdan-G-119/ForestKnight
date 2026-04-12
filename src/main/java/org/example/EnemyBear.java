package org.example;

import java.awt.*;

public class Bear extends Enemy{
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
            g.setColor(new Color(165, 42, 42));
        }
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }

    public Bear(int x, int y) {
        this.x = x;
        this.y = y;
        width = 30;
        height = 30;
        speed = 2;
        damage = 4;
        scoreValue = 60;
        hp = 35;
        isAlive = true;
        knockBackResistance = 0.5F;
    }
}
