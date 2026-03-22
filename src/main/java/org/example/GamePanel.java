package org.example;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

class GamePanel extends JPanel implements KeyListener {
    ArrayList<Enemy> enemies = new ArrayList<>();
    WaveManager waveManager = new WaveManager(enemies);
    boolean upPressed = false;
    boolean downPressed = false;

    boolean leftPressed = false;
    boolean rightPressed = false;

    Player player = new Player();

    int mouseX, mouseY;

    public GamePanel() {
        /*
        Enemy wolf = new Wolf(100, 100);
        enemies.add(wolf);

        Enemy bear = new Bear(200, 200);
        enemies.add(bear);
        */
        setPreferredSize(new Dimension(Game.width, Game.height));

        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(16, e -> {
            update();
            repaint();
        });

        timer.start();
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
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
        Color originalColor = g.getColor();
        if(player.isAlive) {
            player.draw(g, mouseX, mouseY);
            player.attack(enemies, mouseX, mouseY);
       } else {
           g.setColor(Color.RED);
           g.fillRect(player.x, player.y, player.width, player.height);
       }

       g.setColor(originalColor);

       for(Enemy enemy : enemies){
           enemy.draw(g);
       }
    }

    public void update() {
        waveManager.update();
        if(player.isAlive){
            if(player.damageCoolDown > 0) player.damageCoolDown--;
            if (player.hp <= 0){
                player.isAlive = false;
            }

            if (upPressed && player.y > 0) {
                player.y -= player.speed;
            }

            if (leftPressed && player.x > 0) {
                player.x -= player.speed;
            }

            if (downPressed && player.y < getHeight() - player.height) {
                player.y += player.speed;
            }

            if (rightPressed && player.x < getWidth() - player.width) {
                player.x += player.speed;
            }

            Iterator<Enemy> iterator = enemies.iterator();

            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                enemy.update(player);

                if (collisionEnable(enemy) && player.damageCoolDown == 0) {
                    enemy.takeDamage(player);
                    player.damageCoolDown = 30;
                }

                if(enemy.hp <= 0){
                    enemy.isAlive = false;
                    player.score += enemy.scoreValue;
                    iterator.remove();
                }
            }
        }
    }

    private boolean collisionEnable(Enemy enemy) {
        return player.getBounds().intersects(enemy.getBounds());
    }

}
