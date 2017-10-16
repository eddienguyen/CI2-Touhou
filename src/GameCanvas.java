import touhou.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameCanvas extends JPanel {

    BufferedImage backBuffer;
    Graphics backGraphics;

    BufferedImage RightPanel;
    Graphics rightGraphics;

    ArrayList<PlayerSpell> spells = new ArrayList<>();
    ArrayList<EnemySpell> enemySpells = new ArrayList<>();

    Player player = new Player();
    Enemy enemy = new Enemy();
    Ground background = new Ground();


    public GameCanvas() {
        //1.create back buffer
        backBuffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBuffer.getGraphics();
        //2.Load bg:
        //on separated class
    }


    public void render() {
        //1.draw everything on back buffer
        background.render(backGraphics);
        player.render(backGraphics);

        enemy.render(backGraphics);

        for (PlayerSpell spell : spells) {
            spell.render(backGraphics);
        }
        for (EnemySpell spell : enemySpells) {
            spell.render(backGraphics);
        }

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

        player.run();

        player.fireWithRate(spells);

        for (PlayerSpell spell : spells) {
            spell.run(player.X, player.Y);
            spell.checkHitEnemy(enemy);
        }

        //3 spell at a time
        player.tripleShot(spells);
        for (PlayerSpell spell : spells) {
            spell.spellOfThree(player.X, player.Y);
            spell.checkHitEnemy(enemy);
        }


        enemy.run();
        enemy.reLoad(enemySpells);

        for (EnemySpell spell : enemySpells) {
            spell.run();
            spell.checkCollisionWithPlayer(player);
        }
    }//run


}
