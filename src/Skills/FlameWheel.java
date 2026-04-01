package Skills;
import ru.ifmo.se.pokemon.*;

public class FlameWheel extends PhysicalMove{
    public FlameWheel(){
        super(Type.FIRE, 60, 1);
    }

    @Override
    public String describe(){
        return "Использует Flame Wheel";
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        super.applyOppEffects(pokemon);

        if(Math.random() < 0.1)
            Effect.burn(pokemon);
    }
}
