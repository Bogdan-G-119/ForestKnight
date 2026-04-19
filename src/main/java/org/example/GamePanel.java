package org.example;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.MouseEvent;

class GamePanel extends JPanel implements KeyListener, MouseListener {
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<PowerUp> powerUps = new ArrayList<>();
    ArrayList<Arrow> arrows = new ArrayList<>();
    Player player = new Player();
    WaveManager waveManager = new WaveManager(enemies, powerUps);
    boolean upPressed = false;
    boolean downPressed = false;

    boolean leftPressed = false;
    boolean rightPressed = false;
    int mouseX, mouseY;
    Image background;
    public GamePanel() {
        player.crossbow = new WeaponCrossbow(arrows);
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
        background = new ImageIcon(getClass().getResource("/BG.jpg")).getImage();
    }

    boolean mouseClicked = false;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseClicked = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

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

        if(key == KeyEvent.VK_1){
            player.currentWeapon = player.sword;
            player.image = new ImageIcon(getClass().getResource("/Player.png")).getImage();
        }
        if(key == KeyEvent.VK_2){
            player.currentWeapon = player.crossbow;
            player.image = new ImageIcon(getClass().getResource("/PlayerWithBow.png")).getImage();
        }

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
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
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

       for(Arrow arrow : arrows){
           arrow.draw(g);
       }

       /*g.setColor(originalColor);
       g.drawString("HP: " + player.hp, getWidth()/5 - 70, 20);
       g.drawString("score: " + player.score, getWidth()/5 * 2 - 70, 20);
       g.drawString("Wave: " + waveManager.waveNumber, getWidth()/5 * 3 - 70, 20);
       g.drawString("Speed: " + player.speed, getWidth()/5 * 4 - 70, 20);
       g.drawString("Arrows: " + player.arrowsLeft, getWidth() - 70, 20);*/

       g.setColor(Color.DARK_GRAY);
       g.fillRect(10, 10, 200, 80);

       g.setColor(Color.WHITE);
       g.drawString("HP: " + player.hp, 20, 30);
       g.drawString("Arrows: " + player.arrowsLeft, 20, 50);

       g.drawRect(10, getHeight() - 60, 100, 50);
       g.drawString("1: Sword", 15, getHeight() - 40);
       g.drawString("2: Bow", 15, getHeight() - 20);
    }

    public void update() {
        if(player.isAlive){
            player.playerAngle = player.angle(mouseX, mouseY);
            if(player.arrowsLeft > 30){
                player.arrowsLeft = 30;
            }
            player.attack(enemies, mouseX, mouseY, mouseClicked);
            waveManager.update();

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
                    player.arrowsLeft += 5;
                    iterator.remove();
                }
            }
            for(PowerUp power : powerUps){
                if(!power.isCollected){
                    power.checkCollision(player);
                }
            }
            Iterator<Arrow> arrowIterator = arrows.iterator();

            while(arrowIterator.hasNext()){
                Arrow arrow = arrowIterator.next();
                arrow.update();

                for(Enemy enemy : enemies){
                    if(arrow.getBounds().intersects(enemy.getBounds())){
                        enemy.hp -= 5;
                        enemy.hitFlashTime = 5;
                        arrowIterator.remove();
                        break;
                    }
                }
            }
            mouseClicked = false;
        }
    }

    private boolean collisionEnable(Enemy enemy) {
        return player.getBounds().intersects(enemy.getBounds());
    }

}
