package org.example.core;

import org.example.entities.Arrow;
import org.example.entities.Enemy;
import org.example.entities.Player;
import org.example.items.PowerUp;

import java.util.ArrayList;
/**
 * Zdieľaný kontext hry.
 * Obsahuje referencie na herné objekty, ktoré sú potrebné
 * pre komunikáciu medzi entitami.
 */
public class GameContext {
    public Player player;
    public ArrayList<Enemy> enemies;
    public ArrayList<Arrow> arrows;
    public ArrayList<PowerUp> powerUps;

    public GameContext(Player player, ArrayList<Enemy> enemies, ArrayList<Arrow> arrows, ArrayList<PowerUp> powerUps) {
        this.player = player;
        this.enemies = enemies;
        this.arrows = arrows;
        this.powerUps = powerUps;
    }
}