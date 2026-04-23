package org.example.weapons;

import org.example.core.GameContext;
import org.example.entities.Player;

public abstract class Weapon {
        public abstract void attack(Player player, GameContext context, int mouseX, int mouseY, boolean mousePressed);
}
