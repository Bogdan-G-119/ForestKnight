package org.example.items.powerUps;

import org.example.entities.Player;
import org.example.items.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PowerHealth extends PowerUp {
    int extraHp;
    public PowerHealth(int x, int y, int extraHp) {
        super(x, y, new ImageIcon(Objects.requireNonNull(PowerSword.class.getResource("/PowerUpHeal.png"))).getImage());
        this.extraHp = extraHp;
    }


    @Override
    protected Color getColor() {
        return Color.green;
    }

    @Override
    public void apply(Player player) {player.heal(extraHp);}

    @Override
    public void update() {

    }
}
