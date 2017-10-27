package bases;

import bases.Physics.BoxCollider;
import bases.Physics.PhysicsBody;
import touhou.enemies.Enemy;
import touhou.enemies.EnemySpell;
import touhou.players.Player;

import javax.swing.*;
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
                    (int) position.x - image.getWidth() / 2,
                    (int) position.y - image.getHeight() / 2,
                    null);
        }
    }

    public static <T extends PhysicsBody> T collideWith(BoxCollider boxCollider, Class<T> cls) {

        for (GameObject object : gameObjects) {
            if (!object.isActive) continue;
            if (!(object instanceof PhysicsBody)) continue;
            if (!object.getClass().equals(cls)) continue;

            BoxCollider otherBoxCollider = ((PhysicsBody) object).getBoxCollider();
            if (otherBoxCollider.collideWith(boxCollider))
                return (T) object;
        }
        return null;
    }


    //Vector gameObjects:
    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public static <T extends GameObject> T recycle(Class<T> cls) {
        for (GameObject object : gameObjects) {
            if (!object.isActive) {
                if (object.getClass().equals(cls)) {
                    object.isActive = true;
                    return (T) object;
                }
            }
        }
        try {
            T newGameObject = cls.newInstance();
            add(newGameObject);
            return newGameObject;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive) gameObject.run();
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
    public static Enemy getEnemyHitted(BoxCollider boxCollider) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive && gameObject instanceof Enemy) {
                Enemy enemy = (Enemy) gameObject;
                if (enemy.boxCollider.collideWith(boxCollider)) return enemy;
            }
        }
        return null;
    }

    public static Vector2D getPlayerPosition() {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isActive && gameObject instanceof Player) {
                return ((Player) gameObject).position;
            }
        }
        return null;
    }

}
