package touhou.players;

import bases.GameObject;
import touhou.inputs.InputManager;

public class PlayerCastSpell {
    boolean spellDisabled;
    int coolDownCount;
    final int COOLDOWNTIME = 6;     //0,1s : 0,017s(from millisec,=1 frame)

    public void run(Player owner){

        if (spellDisabled) {
            coolDownCount++;
            if (coolDownCount >= COOLDOWNTIME) {
                spellDisabled = false;
                coolDownCount = 0;
            }
            return;         //to block the method
        }

        if (InputManager.instance.xPressed) {

            PlayerSpell spell = GameObject.recycle(PlayerSpell.class);
            spell.position.set(owner.position);
            spellDisabled = true;
        }
    }
}
