package org.example;

import java.util.ArrayList;

public class CrossbowWeapon extends Weapon {

    @Override
    public void attack(Player player, ArrayList<Enemy> enemies, int mouseX, int mouseY, boolean mousePressed) {
        System.out.println("Shoot arrow");
    }
}
