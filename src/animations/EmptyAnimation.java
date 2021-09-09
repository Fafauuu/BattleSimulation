package animations;

import model.objects.units.Unit;

public class EmptyAnimation extends Animation{
    public EmptyAnimation(Unit attacker, Unit target) {
        super(attacker, target);
    }

    @Override
    public void updateAnimation(){
        this.stopAnimation = true;
    }
}
