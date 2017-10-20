import bases.GameObject;
import touhou.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class GameCanvas extends JPanel {

    BufferedImage backBuffer;
    Graphics backGraphics;

    Player player = new Player();
    Enemy enemy = new Enemy();

    Ground background = new Ground();


    public GameCanvas() {
        //1.create back buffer
        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBuffer.getGraphics();
        //2.Load bg: on separated class

        GameObject.add(background);
        GameObject.add(player);
        GameObject.add(enemy);
    }


    public void render() {
        //1.draw everything on back buffer
        GameObject.renderAll(backGraphics);

        //2.Call repaint
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backBuffer, 0, 0, null);
        g.drawString(String.format("HP: %s", player.HP), 400, 500);
        g.drawString(String.format("mana: %s", player.mana), 400, 550);
        g.drawString(String.format("HP: %s", enemy.HP), 400, 50);

        if (player.HP <= 0) {
            g.drawString("YOU DIED!", 450, 300);
            backGraphics = null;
        } else if (enemy.HP <= 0) {
            g.drawString("YOU WIN!", 450, 300);
            backGraphics = null;
        }

    }


    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    public void run() {

        GameObject.runAll();
        if (enemy.isCollide(player)){
            player.HP--;
        }


        /*
        Nếu player chạm vào các element trong GameObject(ngoài player && bg) thì mất máu
        *note: player có thể mất máu nếu chạm vào playerSpell :|
         */
        for (int i =0; i < GameObject.getSize(); i++){
            if ( (!GameObject.getElement(i).equals(player)) && (!GameObject.getElement(i).equals(background)) ){
                if (player.isCollide(GameObject.getElement(i))){
                    player.HP--;
                }
            }
        }



        //3 spell at a time
        //player.tripleShot(spells);
//        for (PlayerSpell spell : spells) {
//            spell.spellOfThree(player.x, player.y);
//            spell.checkHitEnemy(enemy);
//        }



    }//run



}
