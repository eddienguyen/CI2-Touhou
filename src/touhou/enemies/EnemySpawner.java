package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;

import java.util.Random;

public class EnemySpawner extends GameObject {

    FrameCounter frameCounter = new FrameCounter(120);

    Random random = new Random();

    @Override
    public void run() {
        if (frameCounter.run()) {
            frameCounter.reset();
            spawnByRow();
        }
    }

    private void spawnByRow() {
        int randomSpawnX = random.nextInt(2);
        if (randomSpawnX == 1) {
            //fromRight
            for (int i = 0; i < 10; i++) {
                Enemy enemy = new Enemy();
                enemy.position.set(352 - (i * enemy.image.getWidth()),i*5);
                enemy.speedX = -3;
                GameObject.add(enemy);

            }
        } else if (randomSpawnX == 0) {
            //fromLeft
            for (int i = 0; i < 10; i++) {
                Enemy enemy = new Enemy();
                enemy.position.set(i*enemy.image.getWidth(),i*5);
                enemy.speedX = 3;
                GameObject.add(enemy);

            }
        }
    }

    private void spawn() {
        Enemy enemy = new Enemy();
        enemy.position.set(random.nextInt(384),10);
        GameObject.add(enemy);
    }


}
