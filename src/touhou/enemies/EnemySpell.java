package touhou.enemies;

import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Utils;
import bases.Vector2D;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class EnemySpell extends GameObject{
    Vector2D velocity = new Vector2D();
    public BoxCollider boxCollider;
    BufferedImage ground;
    boolean outOfGame;


    public EnemySpell() {
        this.outOfGame = false;
        image = Utils.loadImage("assets/images/enemies/bullets/white.png");
        boxCollider = new BoxCollider(16,16);

        //generate random position for a bullet
        Random rnd = new Random();
        int max = 3, min = -3;
        int randomX = rnd.nextInt(max + 1 - min) + min;
        int randomY = rnd.nextInt(3) + 2;
        velocity.set(randomX,randomY);

    }

    @Override
    public void run() {

        position.addUp(velocity);

        if (position.x < 0) {
            velocity.x = -velocity.x;
        } else if (position.x >= (384 - image.getWidth() - 10)) {
            velocity.x = -velocity.x;
        } else if (position.y > 600) {
            outOfGame = true;
        }

        boxCollider.position.set(this.position);
    }



}
