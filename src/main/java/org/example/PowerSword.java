package org.example;

import java.awt.*;

public class PowerSword extends PowerUp{
    int extraSize;
    public PowerSword(int x, int y, int extraSize) {
        super(x, y);
        this.extraSize = extraSize;
    }

    @Override
    protected Color getColor() {
        return Color.BLUE;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }
    @Override
    public void apply(Player player) {
        player.attackSize += extraSize;
    }
}
