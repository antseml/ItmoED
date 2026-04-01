package items;

import humans.Human;
import interfaces.Cleanable;

public class SpecialSuit extends Item implements Cleanable {
    private boolean isWetInside;
    private boolean isRadiated;
    private String size;

    public SpecialSuit(String size) {
        super("Спецкостюм", 5.0);
        this.size = size;
        this.isWetInside = false;
        this.isRadiated = false;
    }

    @Override
    public Human use(Human user) {
        System.out.println(user.getName() + " осматривает спецкостюм.");
        
        if (isWetInside) {
            System.out.println("Костюм мокрый изнутри.");
        }
        if (isRadiated) {
            System.out.println("Костюм заражен радиацией.");
        }
        if (!isWetInside && !isRadiated) {
            System.out.println("Костюм чистый и сухой.");
        }
        
        return user;
    }

    public SpecialSuit putOn(Human owner) {
        System.out.println(owner.getName() + " надел спецкостюм.");
        return this;
    }

    public SpecialSuit takeOff() {
        System.out.println("Стянул с себя спецкостюм, бросил прямо на пол - холуи-сержанты подберут.");
        return this;
    }

    @Override
    public boolean wash(String waterType, int temperature) {
        System.out.println("Костюм моют в " + waterType + " при температуре " + temperature);
        this.isWetInside = true;
        return true;
    }

    @Override
    public boolean irradiate(int duration, int intensity) {
        System.out.println("Костюм облучают какой-то сволочью " + duration + " минут");
        this.isRadiated = false;
        return true;
    }

    @Override
    public boolean dry(int airTemperature) {
        System.out.println("Костюм сушат при температуре " + airTemperature);
        this.isWetInside = false;
        return true;
    }

    public SpecialSuit dryForDuration(int duration) {
        this.isWetInside = false;
        System.out.println("Костюм просушен за " + duration + " минут.");
        return this;
    }

    public SpecialSuit washWithDetergent(String detergent, int temperature) {
        System.out.println("Костюм вымыт " + detergent + " при температуре " + temperature);
        return this;
    }

    public boolean isWetInside() { return isWetInside; }
    public boolean isRadiated() { return isRadiated; }
    public String getSize() { return size; }
}