package org.example;

import java.util.ArrayList;
import java.util.Random;
public class WaveManager {
    ArrayList<Enemy> enemies;
    int waveNumber;
    boolean waveInProgress;
    Random random = new Random();
    public WaveManager(ArrayList<Enemy> enemies){
        this.enemies = enemies;
        this.waveNumber = 1;
    }

    public void update() {
        if(enemies.isEmpty() && !waveInProgress){
            spawnWave();
            waveNumber++;
            waveInProgress = true;
        }
        if(enemies.isEmpty()){
            waveInProgress = false;
        }
    }

    private void spawnWave() {
        int x;
        int y;
        if(waveNumber == 1){
            for(int i = 0; i < 2; i++){
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

                Enemy wolf = new Wolf(x, y);
                enemies.add(wolf);
            }
        }
    }
}
