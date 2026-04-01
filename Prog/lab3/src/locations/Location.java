package locations;

import humans.Human;
import util.Coordinates;
import java.util.List;

public abstract class Location {
    private String id;
    private String name;
    private Coordinates position;

    public Location(String id, String name, Coordinates position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public abstract boolean enter(List<Human> group);
    public abstract boolean exit(List<Human> group);

    public String getId() { return id; }
    public String getName() { return name; }
    public Coordinates getPosition() { return position; }
}