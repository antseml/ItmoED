package Skills;
import ru.ifmo.se.pokemon.*;

public class CloseCombat extends PhysicalMove {
    public CloseCombat(){
        super(Type.FIGHTING, 120, 1);
    }

    @Override
    public String describe(){
        return "Использует Close Combat";
    }

    @Override
    public void applySelfEffects(Pokemon pokemon){
        super.applySelfEffects(pokemon);

        pokemon.addEffect(
            new Effect().stat(Stat.DEFENSE, -1).stat(Stat.SPECIAL_DEFENSE, -1)
        );
    }
}
