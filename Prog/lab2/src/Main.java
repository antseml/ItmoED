import ru.ifmo.se.pokemon.*;
import Pokemons.*;

public class Main {
    public static void main(String[] args) {
        Battle battle = new Battle();
        battle.addAlly(new Clefable("FT1", 3));
        battle.addFoe(new Clefairy("FT2", 3));
        battle.addAlly(new Cleffa("ST1", 3));
        battle.addFoe(new Crabominable("ST2", 3));
        battle.addAlly(new Crabrawler("TT1", 3));
        battle.addFoe(new Torkoal("TT2", 3));

        battle.go();
    }
}