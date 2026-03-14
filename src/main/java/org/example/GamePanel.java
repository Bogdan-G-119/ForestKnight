package org.example;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;

class GamePanel extends JPanel implements KeyListener {
    public GamePanel() {
        setPreferredSize(new Dimension(Game.width, Game.height));

        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(16, e -> {
            update();
            repaint();
        });

        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W && Player.y > 0){
            Player.y -= 10;
        }
        if(key == KeyEvent.VK_A && Player.x > 0){
            Player.x -= 10;
        }
        if(key == KeyEvent.VK_S && Player.y < getHeight() - Player.height){
            Player.y += 10;
        }
        if(key == KeyEvent.VK_D && Player.x < getWidth() - Player.width){
            Player.x += 10;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Player player = new Player();
        player.draw(g);
    }

    public void update(){

    }
}
