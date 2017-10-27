package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;

import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner extends GameObject {
    final int SPAWNS_EACH_ROLE = 10;
    int spawnCount;
    int level;
    FrameCounter frameCounter = new FrameCounter(120);

    Random random = new Random();

    @Override
    public void run() {
        if (frameCounter.run()) {
            frameCounter.reset();

            spawnByRow();
            spawnCount++;
        }

        if (spawnCount >= SPAWNS_EACH_ROLE) {
            spawnCount = 0;
            level++;
            spawnBoss(level);
        }
    }

    private void spawnBoss(int numOfBoss) {
        ArrayList<BlackBoss> bosses = new ArrayList<>();
        for (BlackBoss boss : bosses) boss.isActive = false;
        bosses.clear();


        for (int i = 1; i <= numOfBoss; i++) {
            BlackBoss newBoss = GameObject.recycle(BlackBoss.class);
            newBoss.position.set(i * newBoss.image.getWidth(), 30);
            bosses.add(newBoss);
        }

        System.out.println("size: " + bosses.size());
    }

    private void spawnByRow() {
        int randomSpawnX = random.nextInt(2);
        if (randomSpawnX == 1) {
            //fromRight
            for (int i = 0; i < 10; i++) {
                Enemy enemy = GameObject.recycle(Enemy.class);
                enemy.position.set(352 - (i * enemy.image.getWidth()), i * 5);
                enemy.speedX = -3;

            }
        } else if (randomSpawnX == 0) {
            //fromLeft
            for (int i = 0; i < 10; i++) {
                Enemy enemy = GameObject.recycle(Enemy.class);
                enemy.position.set(i * enemy.image.getWidth(), i * 5);
                enemy.speedX = 3;

            }
        }
    }

    private void spawn() {
        Enemy enemy = new Enemy();
        enemy.position.set(random.nextInt(384), 10);
        GameObject.add(enemy);
    }


}
