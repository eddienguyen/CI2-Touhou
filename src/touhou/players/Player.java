package touhou.players;

import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Physics.PhysicsBody;
import bases.Utils;
import bases.Vector2D;
import touhou.enemies.Enemy;
import touhou.enemies.EnemySpell;
import touhou.inputs.InputManager;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Player extends GameObject implements PhysicsBody {

    BoxCollider boxCollider;

    Vector2D velocity = new Vector2D();
    final int SPEED = 5;
    final int LEFT = 0;
    final int RIGHT = 384;
    final int TOP = 0;
    final int BOTTOM = 500;

    public int HP = 100;

    PlayerCastSpell playerCastSpell;

    public Player() {
        image = Utils.loadImage("assets/images/players/straight/0.png");
        position.set(182, 500);
        playerCastSpell = new PlayerCastSpell();
        boxCollider = new BoxCollider(10, 10);
    }




    @Override
    public void run() {
        this.move();
        this.shoot();
        boxCollider.position.set(this.position);

    }

    public void getHit() {
        HP--;
    }

    public void move() {
        velocity.set(0, 0);

        if (InputManager.instance.rightPressed) {
            velocity.x += SPEED;
        }
        if (InputManager.instance.leftPressed) {
            velocity.x -= SPEED;
        }
        if (InputManager.instance.upPressed) {
            velocity.y -= SPEED;
        }
        if (InputManager.instance.downPressed) {
            velocity.y += SPEED;
        }

        position.addUp(velocity);

        position.x = (int) Utils.clamp(position.x, LEFT, RIGHT);
        position.y = (int) Utils.clamp(position.y, TOP, BOTTOM);

    }

    public void shoot() {
        playerCastSpell.run(this);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
