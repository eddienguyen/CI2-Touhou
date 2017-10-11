import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameCanvas extends JPanel{

    BufferedImage background, player_straight;

    public GameCanvas(){
        //1.Load bg:
        try {
            background = ImageIO.read(new File("assets/images/background/0.png"));
            player_straight = ImageIO.read(new File("assets/images/players/straight/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2.draw Background:
    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(background,0,0,null);
        g.drawImage(player_straight,175,450,null);

    }
}
