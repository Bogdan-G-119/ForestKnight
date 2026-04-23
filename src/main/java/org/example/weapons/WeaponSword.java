package org.example.weapons;

import org.example.core.GameContext;
import org.example.entities.Enemy;
import org.example.entities.Player;

import java.awt.*;

public class WeaponSword extends Weapon {

    @Override
    public void attack(Player player, GameContext context, int mouseX, int mouseY, boolean mousePressed) {

        if (player.canHit() && mousePressed) {

            Rectangle attackRect = player.getAttackBounds(mouseX, mouseY, player.getAttackSize());

            for (Enemy enemy : context.enemies) {
                if (enemy.getBounds().intersects(attackRect)) {
                    enemy.takeDamage(player.getDamage());
                    enemy.applyKnockBack(player.getX(), player.getY(), 15);
                }
            }
            player.onHit();
        }
    }
}
