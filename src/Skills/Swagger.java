package Skills;
import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove{
    public Swagger(){
        super(Type.NORMAL, 0, 0.85);
    }

    @Override
    public String describe(){
        return "Использует Swagger";
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        super.applyOppEffects(pokemon);

        pokemon.addEffect(
            new Effect().stat(Stat.ATTACK, +2)
        );
        Effect.confuse(pokemon);
    }
}
