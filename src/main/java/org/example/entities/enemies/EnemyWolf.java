package org.example.entities.enemies;

import org.example.entities.Enemy;

import javax.swing.*;
import java.util.Objects;


public class EnemyWolf extends Enemy {
    public EnemyWolf(int x, int y) {
        super(new ImageIcon(Objects.requireNonNull(EnemyWolf.class.getResource("/wolf.png"))).getImage(), 1.0F);
        this.x = x;
        this.y = y;
        width = 20;
        height = 20;
        drawWidth = width * 10;
        drawHeight = height * 10;
        speed = 3;
        damage = 2;
        scoreValue = 30;
        hp = 20;
        isAlive = true;
    }
}
