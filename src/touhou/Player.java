package touhou;

import bases.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    int playerHP = 500;
    boolean xPressed;


    public Player() {
        image = Utils.loadImage("assets/images/players/straight/0.png");
    }

    public void render(Graphics graphics){
        graphics.drawImage(image,X,Y,null);
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

    }


    public void run() {

//        if ((playerHP <= 0) && (bulletsOutOfRange())) System.exit(0);  //when player is dead
//        //@a Linh: để tạm bulletsOutOfRange là lúc đóng chương trình vì vẫn chưa biết kết thúc game như nào cho hợp lý
//
//        if (bulletsOutOfRange()) reLoad();

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
        if ((downPressed)) {vy += SPEED;      //40 = size of titlebar
        }
        X = X + vx;
        Y = Y + vy;

//        checkCollisionWithPlayer();

        X = (int)clamp(X, LEFT , RIGHT - image.getWidth());
        Y = (int)clamp(Y, TOP, BOTTOM);

    }//run

    private float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }

        if (value > max) {
            return max;
        }

        return value;
    }

    public Rectangle getPlayerBounds() {
        return new Rectangle(X, Y, image.getWidth(), image.getHeight());
    }

    public void shoot(ArrayList<PlayerSpell> spells){
        if (xPressed == true){
            PlayerSpell newSpell = new PlayerSpell();
            newSpell.x = X;
            newSpell.y = Y;
            spells.add(newSpell);
        }
    }
}
