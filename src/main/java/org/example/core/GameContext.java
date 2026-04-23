package org.example.core;

import org.example.entities.Arrow;
import org.example.entities.Enemy;

import java.util.ArrayList;

public class GameContext {
    public ArrayList<Enemy> enemies;
    public ArrayList<Arrow> arrows;

    public GameContext(ArrayList<Enemy> enemies, ArrayList<Arrow> arrows) {
        this.enemies = enemies;
        this.arrows = arrows;
    }
}