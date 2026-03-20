package org.example;

import java.awt.*;

public class Bear extends Enemy{
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
    }
}
