package bases;

import com.sun.org.apache.regexp.internal.RE;
import touhou.Enemy;
import touhou.EnemySpell;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

public class GameObject {
    public float x;
    public float y;
    public BufferedImage image;

    static Vector<GameObject> gameObjects = new Vector<>();
    static Vector<GameObject> newGameObjects = new Vector<>();

    public static void runAll() {
        for (GameObject object : gameObjects) {
            object.run();
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics g) {
        for (GameObject object : gameObjects) {
            object.render(g);
        }
    }

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public BufferedImage getImage() {
        return image;
    }

    public static int getSize() {
        return gameObjects.size();
    }

    public static GameObject getElement(int index) {
        return gameObjects.get(index);
    }

    public GameObject() {
    }

    public void render(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, (int) x, (int) y, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
    }

    public void run() {

    }

    public boolean isCollide(GameObject anotherObject) {
        int anotherObjectWidth = anotherObject.getImage().getWidth();
        int anotherObjectHeight = anotherObject.getImage().getHeight();
        int thisWidth = this.getImage().getWidth();
        int thisHeight = this.getImage().getHeight();

        anotherObjectWidth += anotherObject.x;
        anotherObjectHeight += anotherObject.y;
        thisWidth += this.x;
        thisHeight += this.y;

        //if one rectangle is on left side of other
        //  this object is on left side || this obj is on right side
        if (anotherObject.x > thisWidth || this.x > anotherObjectWidth) return false;

        //if one rectangle is above other
        //  this object is above         || this object is under
        if (anotherObject.y > thisHeight || this.y > anotherObjectHeight) return false;


        return true;
    }


}
