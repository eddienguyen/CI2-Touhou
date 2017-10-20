package touhou;

import bases.GameObject;
import bases.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;

public class Player extends GameObject {

    boolean rightPressed, leftPressed, upPressed, downPressed;

    final int SPEED = 5;

    final int LEFT = 0;
    final int RIGHT = 384;
    final int TOP = 0;
    final int BOTTOM = 500;

    public int HP = 100, mana = 200;
    boolean xPressed, zPressed;
    boolean spellDisabled;
    int coolDownCount;
    final int COOLDOWNTIME = 5;
    Point l1, r1;

    public Player() {
        image = Utils.loadImage("assets/images/players/straight/0.png");
        x = 182;
        y = 500;
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

        move();
        shoot();

    }//run

    private void move() {
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
        x = x + vx;
        y = y + vy;

        x = (int) Ground.clamp(x, LEFT, RIGHT - image.getWidth());
        y = (int) Ground.clamp(y, TOP, BOTTOM);
    }


    @Override
    public BufferedImage getImage() {
        return image;
    }

    public void shoot() {
        if (spellDisabled) {
            coolDownCount++;
            if (coolDownCount >= COOLDOWNTIME) {
                spellDisabled = false;
                coolDownCount = 0;
            }
            return;
        }
        if (xPressed == true) {
            PlayerSpell newSpell = new PlayerSpell();
            newSpell.setX(x);
            newSpell.setY(y);
            newSpell.setPosition("middle");

            GameObject.add(newSpell);

            spellDisabled = true;
        }

    }

    public Rectangle getPlayerBounds() {
        return new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
    }

//    public void tripleShot(ArrayList<PlayerSpell> spells) {
//        long currentTime = System.currentTimeMillis();
//        if ((zPressed == true) && (mana > 0)) {
//            if (currentTime > this.nextFire) {
//                PlayerSpell leftSpell = new PlayerSpell();
//                PlayerSpell rightSpell = new PlayerSpell();
//                PlayerSpell middleSpell = new PlayerSpell();
//
//                leftSpell.setX(X);
//                rightSpell.setX(X);
//                middleSpell.setX(X);
//                leftSpell.setY(Y);
//                rightSpell.setY(Y);
//                middleSpell.setY(Y);
//
//                leftSpell.setPosition("left");
//                rightSpell.setPosition("right");
//                middleSpell.setPosition("middle");
//
//                spells.add(leftSpell);
//                spells.add(rightSpell);
//                spells.add(middleSpell);
//
//                this.nextFire = currentTime + fireRate;
//
//            }
//            this.mana--;
//        }
//    }


}
