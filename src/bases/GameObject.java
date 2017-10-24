package bases;

import bases.Physics.BoxCollider;
import touhou.enemies.Enemy;
import touhou.enemies.EnemySpell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class GameObject {
    public Vector2D position;

    static Vector<GameObject> gameObjects = new Vector<>();
    static Vector<GameObject> newGameObjects = new Vector<>();
    public BufferedImage image;

    public boolean isActive;

    public GameObject() {
        position = new Vector2D();
        isActive = true;
    }

    public void run() {

    }

    public void render(Graphics g) {
        if (image != null) {
            g.drawImage(image,
                    (int) position.x - image.getWidth()/2,
                    (int) position.y -image.getHeight()/2,
                    null);
        }
    }

    public static GameObject collided(BoxCollider boxCollider){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive && gameObject instanceof Enemy){
                Enemy enemy = (Enemy) gameObject;
                if (enemy.boxCollider.collideWith(boxCollider)) return enemy;
            }
            if (gameObject.isActive && gameObject instanceof EnemySpell){
                EnemySpell enemySpell = (EnemySpell) gameObject;
                if (enemySpell.boxCollider.collideWith(boxCollider)) return enemySpell;
            }
        }
        return null;
    }


    //Vector gameObjects:
    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive)gameObject.run();
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics g) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive) gameObject.render(g);
        }
    }

    /*
    reference:
     */
    public static Enemy getEnemyHitted(BoxCollider boxCollider){
        for (GameObject gameObject : gameObjects){
            if (gameObject.isActive && gameObject instanceof Enemy){
                Enemy enemy = (Enemy) gameObject;
                if (enemy.boxCollider.collideWith(boxCollider)) return enemy;
            }
        }
        return null;
    }

}
