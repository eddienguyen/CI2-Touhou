package touhou;

import bases.GameObject;
import bases.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends GameObject{

    int y1 = 600;
    int y2 = y1 - 3109;
    public BufferedImage image;

    public Background(){
        image = Utils.loadImage("assets/images/background/0.png");

    }

    @Override
    public void render(Graphics g){
        g.drawImage(image,(int)position.x,y1,null);
        g.drawImage(image,(int)position.x,y2,null);

        y1 += 1;
        y2 += 1;

        if (y1 > 0) y2 = y1 - 3109;
        if (y2 > 0) y1 = y2 - 3109;


    }
    public void run(){

    }


}
