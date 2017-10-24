package touhou.players;

import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Utils;
import touhou.enemies.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpell extends GameObject{

    final int SPEED = 10;
    public BoxCollider boxCollider;

    public PlayerSpell(){
        image = Utils.loadImage("assets/images/player-bullets/a/0.png");
        boxCollider = new BoxCollider(20,20);
    }

    @Override
    public void run(){
        position.subtractBy(0,SPEED);
        boxCollider.position.set(this.position);
        GameObject object = GameObject.collided(this.boxCollider);
        if (object instanceof Enemy){
            Enemy enemy = (Enemy) object;
            enemy.getHit();
            this.isActive = false;
        }
    }
}
