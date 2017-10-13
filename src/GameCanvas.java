import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameCanvas extends JPanel {

    BufferedImage background, player_straight, enemy_stand;
    int playerX = 192;
    int playerY = 450;
    int playerHP = 500;

    int ground1Y = 600;
    int ground2Y = ground1Y - 3109;

    boolean rightPressed, leftPressed, upPressed, downPressed;

    BufferedImage backBuffer;
    Graphics backGraphics;

    ArrayList<Bullet> bullets = new ArrayList<>();
    int amountOfBullets = 10;

    public GameCanvas() {
        //1.create back buffer
        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBuffer.getGraphics();
        //2.Load bg:
        try {
            background = ImageIO.read(new File("assets/images/background/0.png"));
            player_straight = ImageIO.read(new File("assets/images/players/straight/0.png"));
            enemy_stand = ImageIO.read(new File("assets/images/enemies/level0/black/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void render() {
        //1.draw everything on back buffer
        backGraphics.drawImage(background, 0, ground1Y, null);
        backGraphics.drawImage(background, 0, ground2Y, null);


        backGraphics.drawImage(player_straight, playerX, playerY, null);
        backGraphics.drawImage(enemy_stand, background.getWidth() / 2, 0, null);

        //background update: when ground1 touch the edge, replace ground2 position to the end of ground1 and vice versa
        ground1Y += 1;
        ground2Y += 1;
        if (ground1Y > 0) ground2Y = ground1Y - 3109;
        if (ground2Y > 0) ground1Y = ground2Y - 3109;
        //end background update.

        for (Bullet bullet : bullets) {
            bullet.update();
            backGraphics.drawImage(bullet.bulletImg, bullet.x, bullet.y, null);

        }

        //2.Call repaint
        repaint();
    }

    //3.draw Background:
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backBuffer, 0, 0, null);

        g.drawString(String.format("HP: %s", playerHP / 5), 400, 50);
        if (playerHP <= 0) g.drawString("YOU DEAD!", 400, 100);
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
    }

    public void run() {

        if ((playerHP <= 0) && (bulletsOutOfRange())) System.exit(0);  //when player is dead
        //@a Linh: để tạm bulletsOutOfRange là lúc đóng chương trình vì vẫn chưa biết kết thúc game như nào cho hợp lý

        if (bulletsOutOfRange()) reLoad();

        int vx = 0;
        int vy = 0;

        if (rightPressed) {
            if (playerX < (background.getWidth() - player_straight.getWidth())) vx += 5;
        }
        if (leftPressed) {
            if (playerX >= 0) vx -= 5;
        }
        if (upPressed) {
            if (playerY > 0) vy -= 5;
        }
        if ((downPressed)) {
            if (playerY < (backBuffer.getHeight() - player_straight.getHeight() - 40))
                vy += 5;      //40 = size of titlebar
        }
        playerX = playerX + vx;
        playerY = playerY + vy;

        checkCollisionWithPlayer();

    }//run

    public Rectangle getPlayerBounds() {
        return new Rectangle(playerX, playerY, player_straight.getWidth(), player_straight.getHeight());
    }

    public void checkCollisionWithPlayer() {
        for (Bullet bullet : bullets) {
            if (bullet.getBounds().intersects(getPlayerBounds())) {
                playerHP--;
            }
        }

    }

    public void reLoad() {
        for (int i = 0; i <= amountOfBullets; i++) {
            Bullet bullet = new Bullet(background.getWidth() / 2, 0);
            bullets.add(bullet);
        }
    }

    public boolean bulletsOutOfRange() {
        int dumpBullets = 0;
        for (Bullet bullet : bullets) {
            if (bullet.outOfGame) dumpBullets++;
        }
        if (dumpBullets == bullets.size()) return true;
        return false;
    }


}
