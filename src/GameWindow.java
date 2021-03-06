import jdk.internal.util.xml.impl.Input;
import touhou.inputs.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame {

    GameCanvas canvas;

    long lastTimeUpdate;

    public GameWindow() {
        this.setSize(384, 600);

        this.canvas = new GameCanvas();
        this.setContentPane(this.canvas);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                InputManager.instance.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                InputManager.instance.keyReleased(e);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);

        this.canvas.setVisible(true);
        this.setVisible(true);
        lastTimeUpdate = System.nanoTime();
    }

    public void gameLoop() {
        while (true) {

            long currentTime = System.nanoTime();

            if (currentTime - lastTimeUpdate >= 17000000) {
                canvas.run();
                canvas.render();
                lastTimeUpdate = currentTime;
            }

        }
    }


}
