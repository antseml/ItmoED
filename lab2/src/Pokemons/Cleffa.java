package Pokemons;
import ru.ifmo.se.pokemon.*;
import Skills.*;

public class Cleffa extends Pokemon{
    public Cleffa(String name, int level) {
        super(name, level);
        setType(Type.FAIRY);
        setStats(50, 25, 28, 45, 55, 15);
        addMove(new DoubleTeam());
        addMove(new ShadowBall());
    }
}
