package humans;

import util.*;
import interfaces.Cleanable;
import java.util.Objects;

public abstract class Human implements Cleanable {
    private String name;
    private String nickname;
    private int age;
    private CharacterType type;
    private Emotion currentEmotion;
    private HealthStatus health;
    private String backstory;
    private boolean isAlive;

    public Human(String name, int age, CharacterType type) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.currentEmotion = Emotion.CURIOSITY;
        this.health = HealthStatus.ALIVE;
        this.isAlive = true;
    }

    public abstract HealthStatus reactToDanger(ZoneDangerLevel level, String dangerSource);
    public abstract String speak(String phrase, Human listener);

    public Emotion feel(Emotion newEmotion, String reason) {
        this.currentEmotion = newEmotion;
        switch (newEmotion) {
            case FEAR:
                System.out.println(getName() + " почувствовал страх: " + reason);
                break;
            case JOY:
                System.out.println(getName() + " обрадовался: " + reason);
                break;
            case EMPTINESS:
                System.out.println("В коленках пусто, в голове пусто, в душе пусто...");
                break;
            case GRATITUDE:
                System.out.println(getName() + " почувствовал благодарность.");
                break;
            case DESPAIR:
                System.out.println(getName() + " в отчаянии.");
                break;
            default:
                System.out.println(getName() + " испытывает " + newEmotion);
        }
        
        return this.currentEmotion;
    }

    @Override
    public boolean wash(String waterType, int temperature) {
        System.out.println(getName() + " моется в " + waterType + " при температуре " + temperature);
        this.health = HealthStatus.WET;
        return true;
    }

    @Override
    public boolean irradiate(int duration, int intensity) {
        System.out.println(getName() + " облучают какой-то сволочью " + duration + " минут");
        return true;
    }

    @Override
    public boolean dry(int airTemperature) {
        System.out.println(getName() + " сушат при температуре " + airTemperature);
        this.health = HealthStatus.DRY;
        return true;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    
    public int getAge() { return age; }
    
    public CharacterType getType() { return type; }
    
    public Emotion getCurrentEmotion() { return currentEmotion; }
    
    public HealthStatus getHealth() { return health; }
    public void setHealth(HealthStatus health) { 
        this.health = health;
        if (health == HealthStatus.WET) {
            System.out.println(name + " мокрый с головы до ног.");
        } else if (health == HealthStatus.DRY) {
            System.out.println(name + " высох.");
        }
    }
    
    public String getBackstory() { return backstory; }
    public void setBackstory(String backstory) { this.backstory = backstory; }
    
    public boolean isAlive() { return isAlive; }
    public void setAlive(boolean alive) { isAlive = alive; }

    @Override
    public String toString() {
        return (nickname != null ? nickname : name) + " (" + type + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && 
               Objects.equals(name, human.name) && 
               type == human.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, type);
    }
}