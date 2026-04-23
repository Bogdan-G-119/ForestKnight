package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class EnemyKnight extends Enemy{
    public EnemyKnight(int x, int y) {
        this.x = x;
        this.y = y;
        width = 25;
        height = 25;
        drawWidth = width*3;
        drawHeight = height*3;
        speed = 3;
        damage = 2;
        scoreValue = 45;
        hp = 25;
        isAlive = true;
        knockBackResistance = 0.8F;
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Knight.png"))).getImage();
    }
}
