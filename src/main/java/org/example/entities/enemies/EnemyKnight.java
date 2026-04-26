package org.example.entities.enemies;

import org.example.entities.Enemy;

import javax.swing.*;
import java.util.Objects;

/**
 * Konkrétna implementácia nepriateľa - vlk.
 * Má vlastné parametre ako rýchlosť a životy.
 */
public class EnemyKnight extends Enemy {
    public EnemyKnight(int x, int y) {
        super(new ImageIcon(Objects.requireNonNull(EnemyWolf.class.getResource("/Knight.png"))).getImage(), 0.8F);
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
    }
}
