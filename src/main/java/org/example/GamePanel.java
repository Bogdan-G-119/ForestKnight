package org.example;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
        if(player.hp <= 0){
            //remove player, end game
        }
        if(upPressed && player.y > 0){
            player.y -= player.speed;
        }

        if(leftPressed && player.x > 0){
            player.x -= player.speed;
        }

        if(downPressed && player.y < getHeight() - player.height){
            player.y += player.speed;
        }

        if(rightPressed && player.x < getWidth() - player.width){
            player.x += player.speed;
        }

        for(Enemy enemy : enemies){
            enemy.update(player);

            if(collisionEnable(enemy)){
                player.hp--;
            }
        }
    }

    private boolean collisionEnable(Enemy enemy) {
        return player.x < enemy.x + enemy.width &&
                player.x + player.width > enemy.x &&
                player.y < enemy.y + enemy.height &&
                player.y + player.height > enemy.y;
    }
}
