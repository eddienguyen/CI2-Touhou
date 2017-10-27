package touhou.players;

import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Physics.PhysicsBody;
import bases.Utils;
import touhou.enemies.BlackBoss;
import touhou.enemies.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSpell extends GameObject implements PhysicsBody {

    final int SPEED = 10;
    public BoxCollider boxCollider;

    public PlayerSpell() {
        image = Utils.loadImage("assets/images/player-bullets/a/0.png");
        boxCollider = new BoxCollider(20, 20);
    }

    @Override
    public void run() {
        position.subtractBy(0, SPEED);
        boxCollider.position.set(this.position);
        if (position.y <= 0) isActive = false;

        Enemy enemy = GameObject.collideWith(boxCollider, Enemy.class);
        if (enemy != null) {
            isActive = false;
            enemy.isActive = false;
        }

        BlackBoss boss = GameObject.collideWith(boxCollider, BlackBoss.class);
        if (boss != null) {
            isActive = false;
            boss.getHit();
        }


    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
