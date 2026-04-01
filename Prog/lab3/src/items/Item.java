package items;

import humans.Human;
import exceptions.*;

public abstract class Item {
    private String name;
    private double weight;
    private boolean isBroken;

    public Item(String name, double weight) {
        this.name = name;
        this.weight = weight;
        this.isBroken = false;
    }

    public abstract Human use(Human user) throws DeadHumanException, EmptyResourceException;

    @Override
    public String toString() {
        return name;
    }

    public String getName() { return name; }
    public double getWeight() { return weight; }
    public boolean isBroken() { return isBroken; }
    public void setBroken(boolean broken) { isBroken = broken; }
}