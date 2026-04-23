package org.example;

import java.util.ArrayList;

public class WeaponCrossbow extends Weapon {
    ArrayList<Arrow> arrows;

    public WeaponCrossbow(ArrayList<Arrow> arrows){
        this.arrows = arrows;
    }

    @Override
    public void attack(Player player, ArrayList<Enemy> enemies, int mouseX, int mouseY, boolean mousePressed) {
        if(player.attackCoolDown == 0 && mousePressed && player.arrowsLeft > 0){
            arrows.add(new Arrow(player.getX(), player.getY(), mouseX, mouseY));
            player.attackCoolDown = 6;
            player.arrowsLeft--;
        }

        if(player.attackCoolDown > 0){
            player.attackCoolDown--;
        }
    }
}
