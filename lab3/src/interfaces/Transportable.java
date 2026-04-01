package interfaces;

import util.Coordinates;

public interface Transportable {
    Coordinates move(Coordinates target);
    Coordinates getCurrentPosition();
}