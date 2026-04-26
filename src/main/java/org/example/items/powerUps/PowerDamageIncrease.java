package org.example.items.powerUps;
import org.example.entities.Player;
import org.example.items.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
/**
 * Power-up, ktorý zvyšuje poškodenie hráča.
 */
public class PowerDamageIncrease extends PowerUp {
    int extraDamage;
    public PowerDamageIncrease(int x, int y, int extraDamage){
        super(x, y, new ImageIcon(Objects.requireNonNull(PowerSword.class.getResource("/PowerUpDamageIncrease.png"))).getImage());
        this.extraDamage = extraDamage;
    }
    @Override
    protected Color getColor() {
        return Color.RED;
    }
    @Override
    public void apply(Player player) {
        player.increaseDamage(extraDamage);
    }
}
