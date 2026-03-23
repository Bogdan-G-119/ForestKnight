package org.example;

import java.util.ArrayList;
import java.util.Random;
public class WaveManager {
    ArrayList<Enemy> enemies;
    int waveNumber;
    boolean waveInProgress;
    Random random = new Random();
    EnemyType randomType = EnemyType.values()[random.nextInt(EnemyType.values().length)];
    public WaveManager(ArrayList<Enemy> enemies){
        this.enemies = enemies;
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
            for(int i = 0; i < 2; i++){
                spawnEnemy(EnemyType.WOLF);
            }
        }
        if(waveNumber == 2){
            spawnEnemy(randomType);
        }
    }

    public enum EnemyType {
        WOLF, BEAR, KNIGHT_ENEMY
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
}
