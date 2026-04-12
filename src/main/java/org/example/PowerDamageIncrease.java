package org.example;
import java.awt.*;
import java.util.ArrayList;

public class DamageIncreasePower extends PowerUp {
    int extraDamage;
    public DamageIncreasePower(int x, int y, int extraDamage){super(x, y); this.extraDamage = extraDamage;}
    @Override
    protected Color getColor() {
        return Color.RED;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }
    @Override
    public void apply(Player player) {
        player.damage += extraDamage;
    }

}
