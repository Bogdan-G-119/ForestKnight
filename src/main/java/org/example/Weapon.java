package org.example;

import java.util.ArrayList;

public abstract class Weapon {
    public abstract void attack(Player player, ArrayList<Enemy> enemies, int mouseX, int mouseY, boolean mousePressed);

}
