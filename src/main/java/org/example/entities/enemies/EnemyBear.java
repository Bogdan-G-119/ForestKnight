package org.example.entities.enemies;

import org.example.entities.Enemy;

import javax.swing.*;
import java.util.Objects;
/**
 * Konkrétna implementácia nepriateľa - vlk.
 * Má vlastné parametre ako rýchlosť a životy.
 */
public class EnemyBear extends Enemy {
    public EnemyBear(int x, int y) {
        super(new ImageIcon(Objects.requireNonNull(EnemyWolf.class.getResource("/Bear.png"))).getImage(), 0.5F);
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
    }
}
