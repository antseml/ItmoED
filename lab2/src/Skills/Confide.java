package Skills;
import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove{
    public Confide(){
        super(Type.NORMAL, 0, 1);
    }

    @Override
    public String describe(){
        return "Использует Confide";
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        super.applyOppEffects(pokemon);

        pokemon.addEffect(
            new Effect().stat(Stat.SPECIAL_ATTACK, -1)
        );
    }
}
