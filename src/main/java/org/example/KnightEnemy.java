package org.example;

import java.awt.*;

public class KnightEnemy extends Enemy{
    @Override
    public void update(Player player) {
        if(player.x > x){
            x += speed;
        }
        if(player.y > y){
            y += speed;
        }
        if(player.x < x){
            x -= speed;
        }
        if(player.y < y){
            y -= speed;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, width, height);
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
    }
}
