package touhou;

import bases.GameObject;
import bases.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject {
    BufferedImage image;
    int SPEED = 2;
    final int LEFT = 384;
    final int RIGHT = 0;
    final int TOP = 0;
    final int BOTTOM = 500;

    boolean isDeath = false;

    final int loadOfSpells = 3;
    long nextFire;
    final int FIRERATE = 1000;
    public int HP = 500;


    public Enemy() {

        image = Utils.loadImage("assets/images/enemies/level0/black/0.png");
        x = 182;
        y = 0;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    public void render(Graphics graphics) {
        if (HP > 0) {
            graphics.drawImage(image, (int) x, (int) y, null);
        } else isDeath = true;
    }

    public void run() {
        x += SPEED;
        if ((x >= LEFT - image.getWidth()) || (x <= RIGHT)) {
            SPEED = -SPEED;
        }
        shoot();

    }

    public void shoot() {

        long currentTime = System.currentTimeMillis();
        if (!isDeath) {
            if (currentTime > this.nextFire) {
                for (int i = 0; i < loadOfSpells; i++) {
                    EnemySpell newSpell = new EnemySpell();
                    newSpell.x = x;
                    newSpell.y = y;
                    GameObject.add(newSpell);
                }
                this.nextFire = currentTime + FIRERATE;
            }

        }

    }


    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
    }

}
