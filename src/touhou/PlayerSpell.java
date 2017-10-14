package touhou;

import bases.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpell {
    public int x,y;
    final int SPEED = 10 ;
    BufferedImage image;

    public PlayerSpell(){
        image = Utils.loadImage("assets/images/player-bullets/a/1.png");

    }

    public void render(Graphics g){
        g.drawImage(image,x,y,null);
    }
    public void run(){
        y -= SPEED;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,image.getWidth(),image.getHeight());
    }
}
