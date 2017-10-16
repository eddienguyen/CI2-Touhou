package touhou;

import bases.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;

public class Player {
    BufferedImage image;

    public int X = 182;
    public int Y = 500;

    boolean rightPressed, leftPressed, upPressed, downPressed;

    final int SPEED = 5;

    final int LEFT = 0;
    final int RIGHT = 384;
    final int TOP = 0;
    final int BOTTOM = 500;

    public int HP = 100, mana = 200;
    boolean xPressed, zPressed;
    boolean shootable = false;
    long fireRate = 100;
    long nextFire;


    public Player() {
        image = Utils.loadImage("assets/images/players/straight/0.png");
    }

    public void render(Graphics graphics) {
        graphics.drawImage(image, X, Y, null);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_X) xPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_Z) zPressed = true;
    }//keyPressed

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_X) xPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_Z) zPressed = false;

    }


    public void run() {

        int vx = 0;
        int vy = 0;


        if (rightPressed) {
            vx += SPEED;
        }
        if (leftPressed) {
            vx -= SPEED;
        }
        if (upPressed) {
            vy -= SPEED;
        }
        if ((downPressed)) {
            vy += SPEED;
        }
        X = X + vx;
        Y = Y + vy;


        X = (int) Ground.clamp(X, LEFT, RIGHT - image.getWidth());
        Y = (int) Ground.clamp(Y, TOP, BOTTOM);


    }//run


    public Rectangle getPlayerBounds() {

        return new Rectangle(X, Y, image.getWidth(), image.getHeight() / 2);
    }

    public void fireWithRate(ArrayList<PlayerSpell> spells) {        //Code chạy, đhs ?
        long currentTime = System.currentTimeMillis();
        if (xPressed == true) {
            if (currentTime > this.nextFire) {
                PlayerSpell newSpell = new PlayerSpell();
                newSpell.setX(X);
                newSpell.setY(Y);
                newSpell.setPosition("middle");
                spells.add(newSpell);
                this.nextFire = currentTime + fireRate;

            }

        }
    }

    public void tripleShot(ArrayList<PlayerSpell> spells) {
        long currentTime = System.currentTimeMillis();
        if ((zPressed == true) && (mana > 0)) {
            if (currentTime > this.nextFire) {
                PlayerSpell leftSpell = new PlayerSpell();
                PlayerSpell rightSpell = new PlayerSpell();
                PlayerSpell middleSpell = new PlayerSpell();

                leftSpell.setX(X);
                rightSpell.setX(X);
                middleSpell.setX(X);
                leftSpell.setY(Y);
                rightSpell.setY(Y);
                middleSpell.setY(Y);

                leftSpell.setPosition("left");
                rightSpell.setPosition("right");
                middleSpell.setPosition("middle");

                spells.add(leftSpell);
                spells.add(rightSpell);
                spells.add(middleSpell);

                this.nextFire = currentTime + fireRate;

            }
            this.mana--;
        }
    }


}
