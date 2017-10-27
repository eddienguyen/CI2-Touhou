package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Physics.PhysicsBody;
import bases.Utils;
import bases.Vector2D;
import org.w3c.dom.css.Rect;
import touhou.players.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class EnemySpell extends GameObject implements PhysicsBody {
    Vector2D velocity = new Vector2D();
    public BoxCollider boxCollider;
    boolean outOfGame;

    public EnemySpell() {
        this.outOfGame = false;
        image = Utils.loadImage("assets/images/enemies/bullets/white.png");
        boxCollider = new BoxCollider(16, 16);

    }

    @Override
    public void run() {
        Vector2D playerPos = GameObject.getPlayerPosition();

        if (position.y < playerPos.y) {

            if (position.x > playerPos.x) velocity.x -= (float) 0.1;
            if (position.x < playerPos.x) velocity.x += (float) 0.1;
            if (position.x == playerPos.x) velocity.x = 0;

        }
        position.addUp(velocity);

//        if (position.x < 0) {
//            velocity.x = -velocity.x;
//        } else if (position.x >= (384 - image.getWidth() - 10)) {
//            velocity.x = -velocity.x;
//        }
        if (position.y > 600) {
            isActive = false;
        }

        boxCollider.position.set(this.position);

        Player player = GameObject.collideWith(boxCollider, Player.class);
        if (player != null) {
            System.out.println("hit");
            player.getHit();
            isActive = false;
        }
    }


    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
