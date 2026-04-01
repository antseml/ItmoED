package interfaces;

import humans.Newbie;
import util.Coordinates;

public interface StalkerSkill {
    boolean detectAnomaly(Coordinates location, String anomalyType);
    Coordinates navigateBlindly(Coordinates startPoint, Coordinates endPoint);
    boolean calmDown(Newbie newbie, String method);
}