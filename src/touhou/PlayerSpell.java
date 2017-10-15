package touhou;

import bases.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpell {
    public int x,y;
    final int SPEED = 10;
    BufferedImage image;
    String position;

    final int LEFT = 0;
    final int RIGHT = 384;
    final int TOP = 0;
    final int BOTTOM = 500;

    public PlayerSpell(){
        image = Utils.loadImage("assets/images/player-bullets/a/1.png");
        run(x,y);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setPosition(String position){
        this.position = position;
    }
    public String getPosition(){
        return this.position;
    }

    public void render(Graphics g){
        g.drawImage(image,x,y,null);
    }
    public void run(int x, int y){
        setX(this.x);
        setY(this.y -= SPEED);
    }

    public void spellOfThree(int x,int y){
        if (this.position.equalsIgnoreCase("left")){
            setX(this.x -= 2);
        }else if (this.position.equalsIgnoreCase("right")){
            setX(this.x += 2);
        }else if (this.position.equalsIgnoreCase("middle")){
            setX(this.x);
        }

        setY(this.y -= SPEED);

        this.x = (int) clamp(this.x,LEFT,RIGHT - image.getWidth());
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,image.getWidth(),image.getHeight());
    }

    private float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }

        if (value > max) {
            return max;
        }

        return value;
    }
}
