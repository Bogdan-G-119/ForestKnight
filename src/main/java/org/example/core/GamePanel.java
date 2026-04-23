package org.example.core;
import org.example.input.InputHandler;
import org.example.input.InputState;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.util.Objects;

public class GamePanel extends JPanel {
    InputState input = new InputState();
    InputHandler handler = new InputHandler(input);
    GameWorld world;

    public GamePanel() {
        Image background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/BG.jpg"))).getImage();
        Image sword = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Player.png"))).getImage();
        Image crossbow = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PlayerWithBow.png"))).getImage();
        world = new GameWorld(background, sword, crossbow);

        setPreferredSize(new Dimension(Game.width, Game.height));
        setFocusable(true);

        addKeyListener(handler);
        addMouseListener(handler);
        addMouseMotionListener(handler);

        Timer timer = new Timer(16, e -> {
            update();
            repaint();
        });

        timer.start();
    }

    public void update() {
        world.update(input, getWidth(), getHeight());

        input.mouseClicked = false;
        input.key1 = false;
        input.key2 = false;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        world.render(g, getWidth(), getHeight());
    }
}
