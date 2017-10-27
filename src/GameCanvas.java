import bases.GameObject;
import touhou.*;
import touhou.enemies.BlackBoss;
import touhou.enemies.Enemy;
import touhou.enemies.EnemySpawner;
import touhou.enemies.EnemySpell;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameCanvas extends JPanel {

    Background background = new Background();
    Player player = new Player();
    PlayerSpell spell;

    BufferedImage backBuffer;
    Graphics backGraphics;


    public GameCanvas() {
        //1.create back buffer
        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBuffer.getGraphics();
        //2.Load bg:

        GameObject.add(background);
        GameObject.add(player);
        GameObject.add(new EnemySpawner());

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

        g.drawString(String.format("HP: %s", player.HP), 400, 550);

        if (player.HP <= 0){
            g.drawString("YOU DEAD!", 400, 100);                    //doesn't run
            exitGame();
        }

    }

    public void run() {

        GameObject.runAll();

    }//run

    //temp
    public void exitGame(){
        try {
            Thread.sleep(2000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }









}
