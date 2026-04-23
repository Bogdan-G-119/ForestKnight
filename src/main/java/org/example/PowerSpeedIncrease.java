package org.example;

import javax.swing.*;
import java.awt.*;

public class PowerSpeedIncrease extends PowerUp{
    int extraSpeed;
    int duration;
    Image image = new ImageIcon(getClass().getResource("/PowerUpSpeed.png")).getImage();
    public PowerSpeedIncrease(int x, int y, int extraSpeed, int duration) {
        super(x, y);
        this.extraSpeed = extraSpeed;
        this.duration = duration;
    }

    @Override
    protected Color getColor() {
        return Color.pink;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x - (drawWidth - width)/2, y - (drawHeight - height)/2, drawWidth, drawHeight, null);
    }
    @Override
    public void apply(Player player) {
        player.setSpeed(player.getSpeed() + extraSpeed);

        player.addTemporaryEffect(new Player.TemporaryEffect() {
            int timer = duration;

            @Override
            public void update(Player p){
                timer--;
                if(timer <= 0){
                    player.setSpeed(player.getSpeed() - extraSpeed);
                    p.removeTemporaryEffect(this);
                }
            }
        });
    }
}
