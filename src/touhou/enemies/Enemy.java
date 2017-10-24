package touhou.enemies;

import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Utils;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject {
    //final int SPELLEACHSHOT = 3;
    final int FIRERATE = 1000;
    long nextFireTime;
    public BoxCollider boxCollider;
    public int speedX;

    public Enemy(){
        image = Utils.loadImage("assets/images/enemies/level0/blue/0.png");
        position.set(182,0);
        boxCollider = new BoxCollider(30,30);

    }

    @Override
    public void run(){
        super.run();
        this.move();
        boxCollider.position.set(this.position);

    }

    private void move() {
        position.addUp(speedX,2);

        if (position.x > 372 || position.x < 0)   speedX = -speedX;



    }


    public void shoot(ArrayList<EnemySpell> enemySpells){
        long currentTime = System.currentTimeMillis();
        if (currentTime > nextFireTime){
            EnemySpell spell = new EnemySpell();
            spell.position.set(this.position);
            enemySpells.add(spell);

            nextFireTime = currentTime + FIRERATE;
        }

    }

    public void getHit() {
        isActive = false;
    }
}
