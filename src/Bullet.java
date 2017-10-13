import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Bullet {
    int x, y;
    int vx = 1;
    int vy = 1;
    BufferedImage bulletImg;
    BufferedImage ground;
    boolean outOfGame;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = 0;
        this.outOfGame = false;

        try {
            bulletImg = ImageIO.read(new File("assets/images/enemies/bullets/white.png"));
            ground = ImageIO.read(new File("assets/images/background/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //generate random position for a bullet
        Random rnd = new Random();
        int max = 3, min = -3;
        int randomX = rnd.nextInt(max + 1 - min) + min;
        int randomY = rnd.nextInt(3) + 2;
        vx = randomX;
        vy = randomY;

    }

    public void update() {

        x += vx;
        y += vy;

        if (x < 0) {
            vx = -vx;
        } else if (x >= (ground.getWidth() - bulletImg.getWidth() - 10)) {
            vx = -vx;
        } else if (y > 600) {
            outOfGame = true;
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, bulletImg.getWidth(), bulletImg.getHeight());
    }


}
