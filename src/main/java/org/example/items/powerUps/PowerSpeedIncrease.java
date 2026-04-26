package org.example.items.powerUps;

import org.example.entities.Player;
import org.example.items.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
/**
 * Power-up, ktorý zvyšuje rýchlosť pohybu hráča.
 */
public class PowerSpeedIncrease extends PowerUp {
    int extraSpeed;
    int duration;
    public PowerSpeedIncrease(int x, int y, int extraSpeed, int duration) {
        super(x, y, new ImageIcon(Objects.requireNonNull(PowerSword.class.getResource("/PowerUpSpeed.png"))).getImage());
        this.extraSpeed = extraSpeed;
        this.duration = duration;
    }

    @Override
    protected Color getColor() {
        return Color.pink;
    }

    @Override
    public void apply(Player player) {
        player.speedUp(extraSpeed);

        player.addTemporaryEffect(new Player.TemporaryEffect() {
            int timer = duration;

            @Override
            public void update(Player p){
                timer--;
                if(timer <= 0){
                    player.speedUp(-extraSpeed);
                    p.removeTemporaryEffect(this);
                }
            }
        });
    }

}
