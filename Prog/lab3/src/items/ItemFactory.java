package items;

import util.TimeSpent;

public class ItemFactory {
    
    public Item createFlask(double volume, String liquidType) {
        return new Flask(volume, liquidType);
    }
    
    public Item createCigarette(String brand, double length) {
        return new Cigarette(brand, length);
    }
    
    public Item createWatch(String brand, TimeSpent initialTime) {
        return new Watch(brand, initialTime);
    }
    
    public Item createSpecialSuit(String size) {
        return new SpecialSuit(size);
    }
    
    public Item createDummyArtifact(double pull, boolean dangerous, String color) {
        return new DummyArtifact(pull, dangerous, color);
    }
}