package Skills;
import ru.ifmo.se.pokemon.*;

public class Psychic extends SpecialMove{
    public Psychic() {
        super(Type.PSYCHIC, 90, 1);
    }

    @Override
    public String describe() {
        return "Использует Psychic";
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        super.applyOppEffects(pokemon);

        pokemon.addEffect(
            new Effect().chance(0.1).stat(Stat.SPECIAL_DEFENSE, -1)
        );
    }
}
