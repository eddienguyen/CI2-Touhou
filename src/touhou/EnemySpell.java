package touhou;

import bases.Utils;
import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class EnemySpell {
    int x, y;
    int vx = 1;
    int vy = 1;
    BufferedImage image;
    boolean outOfGame;


    public EnemySpell(int x, int y) {
        this.x = x;
        this.y = y;
        this.outOfGame = false;

        image = Utils.loadImage("assets/images/enemies/bullets/white.png");

        //generate random position for a bullet
        Random rnd = new Random();
        int max = 3, min = -3;
        int randomX = rnd.nextInt(max + 1 - min) + min;
        int randomY = rnd.nextInt(3) + 2;
        vx = randomX;
        vy = randomY;

    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
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
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void checkCollisionWithPlayer(Player player) {
        if (this.getBounds().intersects(player.getPlayerBounds())) player.HP--;

    }


}
