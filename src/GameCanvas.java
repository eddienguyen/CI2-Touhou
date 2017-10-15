import touhou.Enemy;
import touhou.EnemySpell;
import touhou.Player;
import touhou.PlayerSpell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameCanvas extends JPanel {

    BufferedImage background, enemy_stand;

    int ground1Y = 600;
    int ground2Y = ground1Y - 3109;

    BufferedImage backBuffer;
    Graphics backGraphics;

    ArrayList<PlayerSpell> spells = new ArrayList<>();
    ArrayList<EnemySpell> enemySpells = new ArrayList<>();

    Player player = new Player();
    Enemy enemy = new Enemy();


    public GameCanvas() {
        //1.create back buffer
        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBuffer.getGraphics();
        //2.Load bg:
        try {
            background = ImageIO.read(new File("assets/images/background/0.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void render() {
        //1.draw everything on back buffer
        backGraphics.drawImage(background, 0, ground1Y, null);
        backGraphics.drawImage(background, 0, ground2Y, null);
        player.render(backGraphics);
        enemy.render(backGraphics);

        for (PlayerSpell spell : spells){
            spell.render(backGraphics);
        }
        for (EnemySpell spell : enemySpells){
            spell.render(backGraphics);
        }



        //background update: when ground1 touch the edge, replace ground2 position to the end of ground1 and vice versa
        ground1Y += 1;
        ground2Y += 1;
        if (ground1Y > 0) ground2Y = ground1Y - 3109;
        if (ground2Y > 0) ground1Y = ground2Y - 3109;
        //end background update.


        //2.Call repaint
        repaint();
    }

    //3.draw Background:
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backBuffer, 0, 0, null);

    }


    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    public void run() {

        player.run();
        //player.shoot(spells);

        player.fireWithRate(spells);

        for (PlayerSpell spell : spells){
            spell.run(player.X,player.Y);
        }

        //3 speel at a time
        player.tripleShot(spells);
        for (PlayerSpell spell : spells){
            spell.spellOfThree(player.X,player.Y);
        }


        enemy.run();
        enemy.reLoad(enemySpells);

        for (EnemySpell spell : enemySpells){
            spell.run();
        }
    }//run



}
