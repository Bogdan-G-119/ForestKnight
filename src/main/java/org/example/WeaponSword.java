package org.example;

import java.awt.*;
import java.util.ArrayList;

public class WeaponSword extends Weapon {

    @Override
    public void attack(Player player, ArrayList<Enemy> enemies, int mouseX, int mouseY, boolean mousePressed){

        if(player.attackCoolDown == 0 && mousePressed){

            Rectangle attackRect = player.getAttackBounds(mouseX, mouseY, player.attackSize);

            for(Enemy enemy : enemies){
                if(enemy.getBounds().intersects(attackRect)){
                    enemy.hp -= player.damage;

                    enemy.applyKnockBack(player.getX(), player.getY(), 5);
                    enemy.hitFlashTime = 5;
                }
            }

            player.attackCoolDown = 30;
        }

        if(player.attackCoolDown > 0){
            player.attackCoolDown--;
        }
    }
}
