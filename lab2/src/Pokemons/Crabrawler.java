package Pokemons;
import ru.ifmo.se.pokemon.*;
import Skills.*;

public class Crabrawler extends Pokemon{
    public Crabrawler(String name, int level){
        super(name, level);
        setType(Type.FIGHTING);
        setStats(47, 82, 57, 42, 47, 63);
        addMove(new DoubleTeam());
        addMove(new Confide());
        addMove(new CloseCombat());
    }    
}
