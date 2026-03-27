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

class GamePanel extends JPanel implements KeyListener, MouseListener {
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<PowerUp> powerUps = new ArrayList<>();
    Player player = new Player();
    WaveManager waveManager = new WaveManager(enemies, powerUps);
    boolean upPressed = false;
    boolean downPressed = false;

    boolean leftPressed = false;
    boolean rightPressed = false;

    int mouseX, mouseY;

    public GamePanel() {
        setPreferredSize(new Dimension(Game.width, Game.height));

        setFocusable(true);
        addKeyListener(this);
        this.addMouseListener(this);

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

    boolean mousePressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mousePressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
       } else {
           g.setColor(Color.RED);
           g.fillRect(player.x, player.y, player.width, player.height);
       }

       g.setColor(originalColor);

       for(Enemy enemy : enemies){
           enemy.draw(g);
       }

       for(PowerUp power : powerUps){
           if(!power.isCollected){
               power.draw(g);
           }
       }

       g.setColor(originalColor);
       g.drawString("HP: " + player.hp, getWidth()/4 - 70, 20);
       g.drawString("score: " + player.score, getWidth()/4 * 2 - 70, 20);
       g.drawString("Wave: " + waveManager.waveNumber, getWidth()/4 * 3 - 70, 20);
       g.drawString("Speed: " + player.speed, getWidth() - 70, 20);
    }

    public void update() {
        player.tick(enemies, mouseX, mouseY, mousePressed);
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
            for(PowerUp power : powerUps){
                if(!power.isCollected){
                    power.checkCollision(player);
                }
            }
        }
    }

    private boolean collisionEnable(Enemy enemy) {
        return player.getBounds().intersects(enemy.getBounds());
    }

}
