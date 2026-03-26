package org.example;

import java.awt.*;

public class SpeedIncreasePower extends PowerUp{
    int extraSpeed;
    int duration;
    public SpeedIncreasePower(int x, int y, int extraSpeed, int duration) {
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
        g.setColor(getColor());
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }
    @Override
    public void apply(Player player) {
        player.speed += extraSpeed;

        player.addTemporaryEffect(new Player.TemporaryEffect() {
            int timer = duration;

            @Override
            public void update(Player p){
                timer--;
                if(timer <= 0){
                    p.speed -= extraSpeed;
                    p.removeTemporaryEffect(this);
                }
            }
        });
    }
}
