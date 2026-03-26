package org.example;

import java.awt.*;

public class Wolf extends Enemy{

    @Override
    public void update(Player player) {
        if(player.x > x){x += speed;}
        if(player.y > y){y += speed;}
        if(player.x < x){x -= speed;}
        if(player.y < y){y -= speed;}
    }

    @Override
    public void draw(Graphics g) {
         g.drawRect(x, y, width, height);
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
    }
}
