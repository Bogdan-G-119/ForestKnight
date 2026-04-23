package org.example.weapons;

import org.example.core.GameContext;
import org.example.entities.Arrow;
import org.example.entities.Player;

public class WeaponCrossbow extends Weapon {

    @Override
    public void attack(Player player, GameContext context, int mouseX, int mouseY, boolean mousePressed) {
        if (player.canShoot() && mousePressed) {
            context.arrows.add(new Arrow(player.getX(), player.getY(), mouseX, mouseY));
            player.onShoot();
        }
    }
}
