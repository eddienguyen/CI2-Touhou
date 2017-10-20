package touhou;

import bases.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ground extends GameObject{
    float value,min,max;

    int ground1Y = 600;
    int ground2Y = ground1Y - 3109;

    public Ground(){
        try {
            image = ImageIO.read(new File("assets/images/background/0.png"));
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }

    public void render(Graphics graphics){
        graphics.drawImage(image,0,ground1Y,null);
        graphics.drawImage(image,0,ground2Y,null);

        //background update: when ground1 touch the edge, replace ground2 position to the end of ground1 and vice versa
        ground1Y += 1;
        ground2Y += 1;
        if (ground1Y > 0) ground2Y = ground1Y - 3109;
        if (ground2Y > 0) ground1Y = ground2Y - 3109;
        //end background update.
    }

    public void run(){

    }

    public static float clamp(float value, float min, float max){
        if (value < min){
            return min;
        }
        if (value > max){
            return max;
        }

        return value;
    }
}
