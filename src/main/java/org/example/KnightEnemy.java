package org.example;

import java.awt.*;

public class KnightEnemy extends Enemy{
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
            g.setColor(Color.CYAN);
        }
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }

    public KnightEnemy(int x, int y) {
        this.x = x;
        this.y = y;
        width = 25;
        height = 25;
        speed = 3;
        damage = 2;
        scoreValue = 45;
        hp = 25;
        isAlive = true;
        knockBackResistance = 0.8F;
    }
}
