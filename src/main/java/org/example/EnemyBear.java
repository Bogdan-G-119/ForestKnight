package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EnemyBear extends Enemy{
    public EnemyBear(int x, int y) {
        this.x = x;
        this.y = y;
        width = 30;
        height = 30;
        drawWidth = width*10;
        drawHeight = height*10;
        speed = 2;
        damage = 4;
        scoreValue = 60;
        hp = 35;
        isAlive = true;
        knockBackResistance = 0.5F;
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Bear.png"))).getImage();
    }
}
