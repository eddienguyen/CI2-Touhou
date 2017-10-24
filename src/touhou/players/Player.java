package touhou.players;

import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Utils;
import bases.Vector2D;
import touhou.enemies.Enemy;
import touhou.enemies.EnemySpell;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Player extends GameObject {

    boolean rightPressed, leftPressed, upPressed, downPressed;
    boolean xPressed;
    BoxCollider boxCollider;

    Vector2D velocity = new Vector2D();
    final int SPEED = 5;
    final int LEFT = 0;
    final int RIGHT = 384;
    final int TOP = 0;
    final int BOTTOM = 500;

    public int HP = 100;

    boolean spellDisabled;
    int coolDownCount;
    final int COOLDOWNTIME = 6;     //0,1s : 0,017s(from millisec,=1 frame)

    public Player() {
        image = Utils.loadImage("assets/images/players/straight/0.png");
        position.set(182, 500);
        spellDisabled = false;
        boxCollider = new BoxCollider(10, 10);
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == VK_RIGHT) {
            rightPressed = true;
        }

        if (e.getKeyCode() == VK_LEFT) {
            leftPressed = true;
        }

        if (e.getKeyCode() == VK_UP) {
            upPressed = true;
        }

        if (e.getKeyCode() == VK_DOWN) {
            downPressed = true;
        }

        if (e.getKeyCode() == VK_X) {
            xPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == VK_RIGHT) {
            rightPressed = false;
        }

        if (e.getKeyCode() == VK_LEFT) {
            leftPressed = false;
        }

        if (e.getKeyCode() == VK_UP) {
            upPressed = false;
        }

        if (e.getKeyCode() == VK_DOWN) {
            downPressed = false;
        }

        if (e.getKeyCode() == VK_X) {
            xPressed = false;
        }
    }

    @Override
    public void run() {
        this.move();
        this.shoot();
        boxCollider.position.set(this.position);
        GameObject enemy = GameObject.collided(this.boxCollider);
        if (enemy instanceof Enemy){
            getHit();
        }
        if (enemy instanceof EnemySpell){
            getHit();
        }

    }

    private void getHit() {
        HP--;
    }

    public void move() {
        velocity.set(0, 0);

        if (rightPressed) {
            velocity.x += SPEED;
        }
        if (leftPressed) {
            velocity.x -= SPEED;
        }
        if (upPressed) {
            velocity.y -= SPEED;
        }
        if (downPressed) {
            velocity.y += SPEED;
        }

        position.addUp(velocity);

        position.x = (int) Utils.clamp(position.x, LEFT, RIGHT);
        position.y = (int) Utils.clamp(position.y, TOP, BOTTOM);

    }

    public void shoot() {
        if (spellDisabled) {
            coolDownCount++;
            if (coolDownCount >= COOLDOWNTIME) {
                spellDisabled = false;
                coolDownCount = 0;
            }
            return;         //to block the method
        }

        if (xPressed) {

            PlayerSpell spell = new PlayerSpell();
            spell.position.set(this.position);
            GameObject.add(spell);
            spellDisabled = true;
        }


    }

}
