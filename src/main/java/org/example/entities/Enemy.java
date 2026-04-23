package org.example.entities;

import java.awt.*;
import java.awt.Rectangle;

public abstract class Enemy {
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;

    protected int drawWidth = 0;
    protected int drawHeight = 0;

    protected int speed = 2;
    protected int hp = 0;
    protected int damage;
    protected boolean isAlive;
    protected int scoreValue;
    private double knockBackX = 0;
    private double knockBackY = 0;
    int knockBackTime = 0;
    int hitFlashTime = 0;
    double angleToPlayer;
    private boolean deathHandled = false;

    protected Image enemyImage;
    protected float knockBackResistance;
    public Enemy(Image image, float knockBackResistance) {
        this.enemyImage = image;
        this.knockBackResistance = knockBackResistance;
    }

    double angle(Player player){
        return Math.atan2(player.getY() - y, player.getX() - x) + Math.PI / 2;
    }

    public void update(Player player){
        hitFlashUpdate();
        knockBackUpdate();

        moveTo(player);

        handleDeath(player);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int centerX = x + width / 2;
        int centerY = y + height / 2;

        g2.rotate(angleToPlayer, centerX, centerY);

        g2.drawImage(enemyImage, x - (drawWidth - width)/2, y - (drawHeight - height)/2, drawWidth, drawHeight, null);

        g2.rotate(-angleToPlayer, centerX, centerY);
    }
    private void hitFlashUpdate() {
        if(hitFlashTime > 0) hitFlashTime--;
    }

    protected void knockBackUpdate() {
        if (knockBackTime > 0) {
            x += (int) knockBackX;
            y += (int) knockBackY;

            knockBackX *= 0.9;
            knockBackY *= 0.9;

            knockBackTime--;
        }
    }
    protected void handleDeath(Player player) {
        if (hp <= 0 && !deathHandled) {
            onDeath(player);
            deathHandled = true;
        }
    }

    private void onDeath(Player player) {
        player.addEnemyKill(scoreValue);
    }
    public boolean isReadyToRemove() {
        return deathHandled;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    public void causeDamage(Player player){
        player.takeDamage(damage);
    }
    public void takeDamage(int damage){
        hp -= damage;
        hitFlashTime = 5;
    }

    public void applyKnockBack(int playerX, int playerY, double force){
        double dx = x - playerX;
        double dy = y - playerY;

        double length = Math.sqrt(dx * dx + dy * dy);
        if(length == 0) length = 1;

        knockBackX = (dx / length) * force * knockBackResistance;
        knockBackY = (dy / length) * force * knockBackResistance;

        knockBackTime = 10;
    }
    public void moveTo(Player player){
        if(player.getX() > x){x += speed;}
        if(player.getY() > y){y += speed;}
        if(player.getX() < x){x -= speed;}
        if(player.getY() < y){y -= speed;}
        angleToPlayer = angle(player);
    }
    public boolean isDead() {
        return hp <= 0;
    }
}
