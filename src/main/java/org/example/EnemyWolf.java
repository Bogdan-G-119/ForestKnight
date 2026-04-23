package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class EnemyWolf extends Enemy{
    public EnemyWolf(int x, int y) {
        this.x = x;
        this.y = y;
        width = 20;
        height = 20;
        drawWidth = width*10;
        drawHeight = height*10;
        speed = 3;
        damage = 2;
        scoreValue = 30;
        hp = 20;
        isAlive = true;
        knockBackResistance = 1.0F;
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Wolf.png"))).getImage();
    }
}
