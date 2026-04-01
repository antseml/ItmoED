package Skills;
import ru.ifmo.se.pokemon.*;

public class IceBeam extends SpecialMove{
    public IceBeam(){
        super(Type.ICE, 90, 1);
    }

    @Override
    public String describe(){
        return "Использует Ice Beam";
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        super.applyOppEffects(pokemon);
        
        if(Math.random() < 0.1)
            Effect.freeze(pokemon);
    }
}
