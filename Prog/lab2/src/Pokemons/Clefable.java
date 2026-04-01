package Pokemons;
import ru.ifmo.se.pokemon.*;
import Skills.*;

public class Clefable extends Clefairy {
    public Clefable(String name, int level) {
        super(name, level);
        setType(Type.FAIRY);
        setStats(95, 70, 73, 95, 90, 60);
        addMove(new Psychic());
    }
}
