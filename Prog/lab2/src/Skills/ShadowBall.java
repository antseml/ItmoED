package Skills;
import ru.ifmo.se.pokemon.*;

public class ShadowBall extends SpecialMove{
    public ShadowBall(){
        super(Type.GHOST, 80, 1);
    }

    @Override
    public String describe() {
        return "Использует Shadow Ball";
    }

    @Override
    public void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);

        pokemon.addEffect(
            new Effect().chance(0.2).stat(Stat.SPECIAL_DEFENSE, -1)
        );
    }
}
