package org.example;

import java.awt.*;

public abstract class PowerUp {
    int x, y;
    int width = 10;
    int height = 10;
    boolean isCollected = false;

    public PowerUp(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics g);

    protected abstract java.awt.Color getColor();

    public abstract void apply(Player player);

    public void checkCollision(Player player){
        Rectangle playerRect = player.getBounds();
        Rectangle powerRect = new Rectangle(x, y, width, height);
        if(playerRect.intersects(powerRect)){
            isCollected = true;
            apply(player);
        }
    }
}
