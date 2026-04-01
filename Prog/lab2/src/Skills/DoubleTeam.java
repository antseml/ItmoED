package Skills;
import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove{
    public DoubleTeam(){
        super(Type.NORMAL, 0, 1);
    }

    @Override
    public String describe() {
        return "Использует Double Team";
    }
    
    @Override
    public void applySelfEffects(Pokemon pokemon) {
        super.applySelfEffects(pokemon);

        pokemon.addEffect(
            new Effect().stat(Stat.EVASION, +1)
        );
    }
}
