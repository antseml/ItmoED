package Pokemons;
import ru.ifmo.se.pokemon.*;
import Skills.*;

public class Torkoal extends Pokemon{
    public Torkoal(String name, int level){
        super(name, level);
        setType(Type.FIRE);
        setStats(70, 85, 140, 85, 70, 20);
        addMove(new Swagger());
        addMove(new Flamethrower());
        addMove(new FlameWheel());
        addMove(new Withdraw());
    }
}
