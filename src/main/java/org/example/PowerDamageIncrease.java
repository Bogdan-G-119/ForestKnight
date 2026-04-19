package org.example;
import javax.swing.*;
import java.awt.*;

public class PowerDamageIncrease extends PowerUp {
    int extraDamage;
    Image image = new ImageIcon(getClass().getResource("/PowerUpDamageIncrease.png")).getImage();
    public PowerDamageIncrease(int x, int y, int extraDamage){super(x, y); this.extraDamage = extraDamage;}
    @Override
    protected Color getColor() {
        return Color.RED;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x - (drawWidth - width)/2, y - (drawHeight - height)/2, drawWidth, drawHeight, null);
    }
    @Override
    public void apply(Player player) {
        player.damage += extraDamage;
    }

}
