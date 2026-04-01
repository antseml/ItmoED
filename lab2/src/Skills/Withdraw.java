package Skills;
import ru.ifmo.se.pokemon.*;

public class Withdraw extends StatusMove{
    public Withdraw(){
        super(Type.WATER, 0, 1);
    }

    @Override
    public String describe(){
        return "Использует Withdraw";
    }

    @Override
    public void applySelfEffects(Pokemon pokemon){
        super.applySelfEffects(pokemon);

        pokemon.addEffect(
            new Effect().stat(Stat.DEFENSE, +1)
        );
    }
}
