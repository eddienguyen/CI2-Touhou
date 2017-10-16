package touhou;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PowerUp {
    int x, y;
    BufferedImage redImage, blueImage;
    String type;

    public PowerUp(String type) {
        try {
            if (type.equalsIgnoreCase("health")) {
                redImage = ImageIO.read(new File("assets/images/items/power-up-red.png"));
            } else if (type.equalsIgnoreCase("mana")) {
                blueImage = ImageIO.read(new File("assets/images/items/power-up-blue.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics graphics) {
        if (type.equalsIgnoreCase("health")) {
            graphics.drawImage(redImage, 0, 0, null);
        } else if (type.equalsIgnoreCase("mana")) {
            graphics.drawImage(blueImage, 0, 0, null);
        }
    }

    public void run() {

    }

}
