package org.example;

import java.awt.*;

public class SwordPower extends PowerUp{
    int extraSize;
    public SwordPower(int x, int y, int extraSize) {
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
