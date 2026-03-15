package org.example;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.util.ArrayList;

class GamePanel extends JPanel implements KeyListener {
    ArrayList<Enemy> enemies = new ArrayList<>();
    boolean upPressed = false;
    boolean downPressed = false;

    boolean leftPressed = false;
    boolean rightPressed = false;

    Player player = new Player();

    public GamePanel() {
        Enemy wolf = new Wolf(100, 100);
        enemies.add(wolf);

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
        if(key == KeyEvent.VK_W){
            upPressed = true;
        }
        if(key == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(key == KeyEvent.VK_S){
            downPressed = true;
        }
        if(key == KeyEvent.VK_D){
            rightPressed = true;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            upPressed = false;
        }
        if(key == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(key == KeyEvent.VK_S){
            downPressed = false;
        }
        if(key == KeyEvent.VK_D){
            rightPressed = false;
        }

    }

   public void paintComponent(Graphics g){
        super.paintComponent(g);
        player.draw(g);
        for(Enemy enemy : enemies){
            enemy.draw(g);
        }
    }

    public void update(){
        if(upPressed && player.y > 0){
            player.y -= 5;
        }

        if(leftPressed && player.x > 0){
            player.x -= 5;
        }

        if(downPressed && player.y < getHeight() - player.height){
            player.y += 5;
        }

        if(rightPressed && player.x < getWidth() - player.width){
            player.x += 5;
        }

        for(Enemy enemy : enemies){
            enemy.update(player);
        }
    }
}
