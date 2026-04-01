package Skills;
import ru.ifmo.se.pokemon.*;

public class Flamethrower extends SpecialMove{
    public Flamethrower(){
        super(Type.FIRE, 90, 1);
    }

    @Override
    public String describe(){
        return "Использует Flamethrower";
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        super.applyOppEffects(pokemon);

        if(Math.random() < 0.1)
            Effect.burn(pokemon);
    }
}
