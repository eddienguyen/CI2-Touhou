package touhou.enemies;

import bases.GameObject;
import bases.Physics.BoxCollider;
import bases.Utils;

import java.util.ArrayList;

public class BlackBoss extends Enemy{
    final int FIRERATE = 1000;
    long nextFireTime;
    public int HP = 100;

    public BlackBoss(){
        image = Utils.loadImage("assets/images/enemies/level0/black/0.png");
        position.set(182,0);
        boxCollider = new BoxCollider(30,30);

    }

    public void run(){

        boxCollider.position.set(this.position);
        shoot();
    }

    public void shoot(){
        long currentTime = System.currentTimeMillis();
        if (currentTime > nextFireTime){
            EnemySpell spell = new EnemySpell();
            spell.position.set(this.position);
            GameObject.add(spell);

            nextFireTime = currentTime + FIRERATE;
        }

    }

    public void getHit() {
        HP--;
        if (HP <= 0) isActive = false;
    }
}
