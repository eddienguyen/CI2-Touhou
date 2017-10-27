package touhou.enemies;

import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Physics.PhysicsBody;
import bases.Utils;

import java.util.ArrayList;

public class BlackBoss extends Enemy  {
    final int FIRERATE = 1000;
    long nextFireTime;
    public int HP;

    public BlackBoss(){
        image = Utils.loadImage("assets/images/enemies/level0/black/0.png");
        position.set(182,0);
        boxCollider = new BoxCollider(30,30);
        this.HP = 100;

    }

    public void run(){

        boxCollider.position.set(this.position);
        shoot();
    }

    public void shoot(){
        long currentTime = System.currentTimeMillis();
        if (currentTime > nextFireTime){
            EnemySpell spell = GameObject.recycle(EnemySpell.class);
            spell.position.set(this.position);
            spell.velocity.set(0,3);

            nextFireTime = currentTime + FIRERATE;
        }

    }

    public void getHit() {
        HP--;
        if (HP <= 0) isActive = false;
    }
}
