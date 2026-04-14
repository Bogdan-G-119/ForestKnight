package org.example;
import java.awt.*;

public class PowerDamageIncrease extends PowerUp {
    int extraDamage;
    public PowerDamageIncrease(int x, int y, int extraDamage){super(x, y); this.extraDamage = extraDamage;}
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
