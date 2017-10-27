package touhou.enemies;

import bases.GameObject;
import bases.Physics.BoxCollider;
import touhou.players.Player;

public class PlayerDamage {
    public void run(Enemy owner){
        BoxCollider boxCollider = owner.getBoxCollider();
        Player player = GameObject.collideWith(boxCollider,Player.class);
        if (player != null){
            player.getHit();
        }
    }
}
