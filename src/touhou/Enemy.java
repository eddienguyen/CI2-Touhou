package touhou;

import bases.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy {
    int x = 182;
    int y = 0;
    BufferedImage image;
    int SPEED = 2;


    final int loadOfSpells = 20;


    public Enemy(){
        image = Utils.loadImage("assets/images/enemies/level0/black/0.png");
    }

    public void render(Graphics graphics){
        graphics.drawImage(image,x,y,null);
    }

    public void run(){
        x += SPEED;
        if ( (x >= 384-image.getWidth()) || (x<=0) ){
            SPEED = -SPEED;
        }


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

    public void shoot(ArrayList<EnemySpell> spells){
        for (int i = 0; i<loadOfSpells; i++){
            EnemySpell newSpell = new EnemySpell(this.x,this.y);
            spells.add(newSpell);
        }
    }

    public boolean spellsOutOfRange(ArrayList<EnemySpell> spells) {
        int dumpSpells = 0;
        for (EnemySpell spell : spells) {
            if (spell.outOfGame) dumpSpells++;
        }
        if (dumpSpells == spells.size()) return true;
        return false;
    }

    public void reLoad(ArrayList<EnemySpell> spells) {
        if (spellsOutOfRange(spells)) {
            shoot(spells);
        }
    }
}
