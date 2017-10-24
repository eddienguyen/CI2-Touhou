package bases;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utils {


    public static BufferedImage loadImage(String url){
        try {
            BufferedImage image = ImageIO.read(new File(url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
