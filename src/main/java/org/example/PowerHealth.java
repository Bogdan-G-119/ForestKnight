package org.example;

import java.awt.*;

public class PowerHealth extends PowerUp{
    int extraHp;

    public PowerHealth(int x, int y, int extraHp) {
        super(x, y);
        this.extraHp = extraHp;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawRect(x, y, width, height);
        g.fillRect(x, y, width, height);
    }

    @Override
    protected Color getColor() {
        return Color.green;
    }

    @Override
    public void apply(Player player) {player.hp += extraHp;}

}
