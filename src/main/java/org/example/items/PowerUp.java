package org.example.items;

import org.example.Drawable;
import org.example.Updatable;
import org.example.core.GameContext;
import org.example.entities.Player;

import java.awt.*;

public abstract class PowerUp implements Updatable, Drawable {
    private int x, y;
    private int width = 10;
    private int height = 10;
    private int drawWidth = width*4;
    private int drawHeight = height*4;
    private boolean isCollected = false;
    Image powerUpImage;
    public PowerUp(int x, int y, Image powerUpImage){
        this.x = x;
        this.y = y;
        this.powerUpImage = powerUpImage;
    }
    public void update(GameContext context){
        for(PowerUp power : context.powerUps){
            if(!power.isCollected()){
                power.checkCollision(context.player);
            }
        }
    }
    public boolean isCollected() {
        return isCollected;
    }
    public void collect(Player player) {
        if (!isCollected) {
            isCollected = true;
            apply(player);
        }
    }
    public void draw(Graphics g) {
        if (isCollected()){
            return;
        }
        g.drawImage(powerUpImage, x - (drawWidth - width)/2, y - (drawHeight - height)/2, drawWidth, drawHeight, null);
    }

    protected abstract java.awt.Color getColor();

    public abstract void apply(Player player);

    public void checkCollision(Player player){
        if(player.getBounds().intersects(new Rectangle(x, y, width, height))){
            collect(player);
        }
    }
}
