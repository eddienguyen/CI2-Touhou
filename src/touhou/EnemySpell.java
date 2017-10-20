package touhou;

import bases.GameObject;
import bases.Utils;
import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class EnemySpell extends GameObject {
    int vx = 1;
    int vy = 1;
    boolean outOfGame;


    public EnemySpell() {
        image = Utils.loadImage("assets/images/enemies/bullets/white.png");
        this.outOfGame = false;

        //generate random position for a bullet
        Random rnd = new Random();
        int max = 3, min = -3;
        int randomX = rnd.nextInt(max + 1 - min) + min;
        int randomY = rnd.nextInt(3) + 2;
        vx = randomX;
        vy = randomY;

    }


    public void run() {

        x += vx;
        y += vy;

        if (x < 0) {
            vx = -vx;
        } else if (x >= (385 - image.getWidth() - 10)) {
            vx = -vx;
        } else if (y > 600) {
            outOfGame = true;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
    }


}
