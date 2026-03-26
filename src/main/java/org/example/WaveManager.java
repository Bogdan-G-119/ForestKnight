package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
public class WaveManager {
    ArrayList<Enemy> enemies;
    ArrayList<PowerUp> powerUps;
    int waveNumber;
    boolean waveInProgress;
    Random random = new Random();
    EnemyType randomType = EnemyType.values()[random.nextInt(EnemyType.values().length)];
    PowerType randomPower = PowerType.values()[random.nextInt(PowerType.values().length)];
    public WaveManager(ArrayList<Enemy> enemies, ArrayList<PowerUp> powerUps){
        this.enemies = enemies;
        this.powerUps = powerUps;
        this.waveNumber = 0;
    }

    public void update() {
        if(enemies.isEmpty() && !waveInProgress){
            if(waveNumber < 3) waveNumber++;
            spawnWave();
            waveInProgress = true;
        }
        if(enemies.isEmpty()){
            waveInProgress = false;
        }
    }

    private void spawnWave() {
        if(waveNumber == 1){
            for(int i = 0; i < 5; i++){
                spawnEnemy(EnemyType.WOLF);
            }
            spawnPowerUp(PowerType.DAMAGE);
            spawnPowerUp(PowerType.HP);
            spawnPowerUp(PowerType.SPEED);
            spawnPowerUp(PowerType.SWORD);
        }
        if(waveNumber == 2){
            spawnEnemy(randomType);
            spawnPowerUp(randomPower);
        }
    }

    public enum EnemyType {
        WOLF, BEAR, KNIGHT_ENEMY
    }

    public enum PowerType {
        DAMAGE, HP, SPEED, SWORD
    }

    private int[] getRandomSpawnPosition(){
        int x, y;
        int side = random.nextInt(4);
        if(side == 0){
            x = 0;
            y = random.nextInt(Game.height);
        } else if(side == 1){
            x = Game.width;
            y = random.nextInt(Game.height);
        } else if(side == 2){
            x = random.nextInt(Game.width);
            y = 0;
        } else {
            x = random.nextInt(Game.width);
            y = Game.height;
        }
        return new int[]{x, y};
    }

    private int[] getRandomSpawnPositionForPower(){
        int x, y;
        int barier = 20;
        int side = random.nextInt(4);
        if(side == 0){
            x = barier;
            y = random.nextInt(Game.height - barier);
        } else if(side == 1){
            x = Game.width - barier;
            y = random.nextInt(Game.height - barier);
        } else if(side == 2){
            x = random.nextInt(Game.width - barier);
            y = 0;
        } else {
            x = random.nextInt(Game.width - barier);
            y = Game.height - barier;
        }
        return new int[]{x, y};
    }

    private void spawnEnemy(EnemyType type){
        int[] pos = getRandomSpawnPosition();
        int x = pos[0];
        int y = pos[1];

        switch (type) {
            case WOLF:
                enemies.add(new Wolf(x, y));
                break;
            case BEAR:
                enemies.add(new Bear(x, y));
                break;
            case KNIGHT_ENEMY:
                enemies.add(new KnightEnemy(x, y));
                break;
        }
    }

    private void spawnPowerUp(PowerType type){
        int[] pos = getRandomSpawnPositionForPower();
        int x = pos[0];
        int y = pos[1];

        switch (type) {
            case DAMAGE:
                powerUps.add(new DamageIncreasePower(x, y, 4));
                break;
            case HP:
                powerUps.add(new HealthPower(x, y, 5));
                break;
            case SPEED:
                powerUps.add(new SpeedIncreasePower(x, y, 1, 300));
                break;
            case SWORD:
                powerUps.add(new SwordPower(x, y, 10));
                break;
        }
    }
}
