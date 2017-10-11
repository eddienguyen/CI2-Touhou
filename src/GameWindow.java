import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameWindow extends JFrame{

    GameCanvas canvas;
    public GameWindow()  {
        this.setSize(800,600);

        this.canvas = new GameCanvas();
        this.setContentPane(this.canvas);

        this.canvas.setVisible(true);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
    }




}
