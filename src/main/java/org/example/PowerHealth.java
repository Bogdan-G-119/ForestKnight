package org.example;

import javax.swing.*;
import java.awt.*;

public class PowerHealth extends PowerUp{
    int extraHp;
    Image image = new ImageIcon(getClass().getResource("/PowerUpHeal.png")).getImage();
    public PowerHealth(int x, int y, int extraHp) {
        super(x, y);
        this.extraHp = extraHp;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x - (drawWidth - width)/2, y - (drawHeight - height)/2, drawWidth, drawHeight, null);
    }

    @Override
    protected Color getColor() {
        return Color.green;
    }

    @Override
    public void apply(Player player) {player.hp += extraHp;}

}
