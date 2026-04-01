package items;

import humans.Human;
import exceptions.EmptyResourceException;
import util.Emotion;

public class Cigarette extends Item {
    private boolean isLit;
    private double remainingLength;
    private String brand;

    public Cigarette(String brand, double length) {
        super("Сигарета", 0.01);
        this.brand = brand;
        this.remainingLength = length;
        this.isLit = false;
    }

    @Override
    public Human use(Human user) throws EmptyResourceException {
        if (!isLit) {
            System.out.println("Сигарета не зажжена. Надо прикурить.");
            return user;
        }
        
        if (remainingLength <= 0) {
            throw new EmptyResourceException("Сигарета закончилась");
        }
        
        System.out.println(user.getName() + " затягивается сигаретой. Закурил и сидит... Чувствует - отходить начал.");
        
        user.feel(Emotion.GRATITUDE, "возможность отдохнуть");
        
        remainingLength -= 0.3;
        
        if (remainingLength <= 0) {
            System.out.println("Сигарета догорела.");
        }
        
        return user;
    }

    public Cigarette light() {
        this.isLit = true;
        System.out.println("Закурил сигарету.");
        return this;
    }

    public Cigarette extinguish() {
        this.isLit = false;
        System.out.println("Сигарета потушена.");
        return this;
    }

    public boolean isFinished() {
        return remainingLength <= 0;
    }

    
    public boolean isLit() { return isLit; }
    public double getRemainingLength() { return remainingLength; }
    public String getBrand() { return brand; }
}