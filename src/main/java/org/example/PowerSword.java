package org.example;

import javax.swing.*;
import java.awt.*;

public class PowerSword extends PowerUp{
    int extraSize;
    Image image = new ImageIcon(getClass().getResource("/PowerUpSword.png")).getImage();
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
        g.drawImage(image, x - (drawWidth - width)/2, y - (drawHeight - height)/2, drawWidth, drawHeight, null);
    }
    @Override
    public void apply(Player player) {
        player.attackSize += extraSize;
    }
}
