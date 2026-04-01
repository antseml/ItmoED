package items;

import humans.Human;
import exceptions.EmptyResourceException;
import util.Emotion;
import util.HealthStatus;

public class Flask extends Item {
    private boolean isFull;
    private double volume;
    private boolean hasCap;
    private String liquidType;

    public Flask(double volume, String liquidType) {
        super("Фляга", 0.5);
        this.volume = volume;
        this.liquidType = liquidType;
        this.isFull = volume > 0;
        this.hasCap = true;
    }

    @Override
    public Human use(Human user) throws EmptyResourceException {       
        if (!isFull) {
            throw new EmptyResourceException(user.getName() + " хочет отпить, но фляга пуста");
        }
        System.out.println(user.getName() + " отвинтил колпачок и присосался к фляге, как клоп.");
        if (user.getHealth() == HealthStatus.WET) {
            System.out.println("Сидит на лавочке мокрый, в коленках пусто, в голове пусто, в душе пусто, знай себе глотает крепкое, как воду.");
        } else {
            System.out.println("Глотает крепкое, как воду.");
        }

        user.setHealth(HealthStatus.INTOXICATED);
        user.feel(Emotion.EMPTINESS, "глоток из фляги");
        
        this.volume -= 0.2;
        if (this.volume <= 0) {
            this.isFull = false;
            System.out.println("Высосал флягу досуха. Сам мокрый, фляга сухая. Одного последнего глотка, конечно, не хватило.");
        } else {
            System.out.println("И текут по щекам слезы - то ли от крепкого, то ли не знаю от чего.");
        }
        
        return user;
    }

    public Flask screwCap(boolean tight) {
        this.hasCap = tight;
        if (tight) {
            System.out.println("Закрутил колпачок.");
        } else {
            System.out.println("Открыл флягу.");
        }
        return this;
    }

    public String getTaste() {
        return liquidType;
    }

    public Flask emptyFlush() {
        this.isFull = false;
        this.volume = 0;
        System.out.println("Фляга опустошена досуха.");
        return this;
    }

    public Flask fill(String liquid, double volume) {
        this.liquidType = liquid;
        this.volume = volume;
        this.isFull = volume > 0;
        System.out.println("Фляга наполнена " + liquid + ".");
        return this;
    }

    public boolean isFull() { return isFull; }
    public double getVolume() { return volume; }
    public boolean hasCap() { return hasCap; }
}