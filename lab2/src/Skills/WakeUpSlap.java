package Skills;
import ru.ifmo.se.pokemon.*;

public class WakeUpSlap extends PhysicalMove{
    public WakeUpSlap() {
        super(Type.FIGHTING, 70, 1);
    }

    @Override
    public String describe(){
        return "Использует Wake-Up Slap";
    }
}
