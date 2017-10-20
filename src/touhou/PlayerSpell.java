package touhou;

import bases.GameObject;
import bases.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpell extends GameObject {
    final int SPEED = 10;

    String position;

    final int LEFT = 0;
    final int RIGHT = 384;
    final int TOP = 0;
    final int BOTTOM = 500;

    public PlayerSpell() {
        image = Utils.loadImage("assets/images/player-bullets/a/1.png");
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void run() {
        setY(this.y -= SPEED);
    }



    /*
    unused method
     */
    public void spellOfThree(float x, float y) {
        if (this.position.equalsIgnoreCase("left")) {
            setX(this.x -= 2);
        } else if (this.position.equalsIgnoreCase("right")) {
            setX(this.x += 2);
        } else if (this.position.equalsIgnoreCase("middle")) {
            setX(this.x);
        }

        setY(this.y -= SPEED);

        this.x = (int) Ground.clamp(this.x, LEFT, RIGHT - image.getWidth());
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
    }


}
