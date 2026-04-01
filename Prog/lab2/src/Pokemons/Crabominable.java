package Pokemons;
import ru.ifmo.se.pokemon.*;
import Skills.*;

public class Crabominable extends Crabrawler{
    public Crabominable(String name, int level){
        super(name, level);
        setType(Type.FIGHTING, Type.ICE);
        setStats(97, 132, 77, 62, 67, 43);
        addMove(new IceBeam());
    }
}
