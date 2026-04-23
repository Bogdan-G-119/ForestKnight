package org.example.items.powerUps;

import org.example.entities.Player;
import org.example.items.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PowerSword extends PowerUp {
    int extraSize;
    public PowerSword(int x, int y, int extraSize) {
        super(x, y, new ImageIcon(Objects.requireNonNull(PowerSword.class.getResource("/PowerUpSword.png"))).getImage());
        this.extraSize = extraSize;
    }

    @Override
    protected Color getColor() {
        return Color.BLUE;
    }

    @Override
    public void apply(Player player) {
        player.increaseHitBox(extraSize);
    }
}
