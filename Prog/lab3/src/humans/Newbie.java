package humans;

import util.*;
import transport.Galosha;
import java.util.List;

public class Newbie extends Human {
    private boolean isTrained;
    private boolean isShouting;

    public Newbie(String name, int age) {
        super(name, age, CharacterType.NEWBIE);
        this.isTrained = false;
        this.isShouting = false;
    }

    @Override
    public HealthStatus reactToDanger(ZoneDangerLevel level, String dangerSource) {
        if (level != ZoneDangerLevel.SAFE) {
            feel(Emotion.FEAR, dangerSource);
            isShouting = true;
            setHealth(HealthStatus.TIRED);
            System.out.println(getName() + " в панике!");
        }
        return getHealth();
    }

    @Override
    public String speak(String phrase, Human listener) {
        String speech = getName() + " (новичок) говорит " + listener.getName() + ": \"" + phrase + "\"";
        System.out.println(speech);
        return speech;
    }

    public Stalker panic(String reason, Stalker stalker) {
        feel(Emotion.FEAR, reason);
        isShouting = true;
        System.out.println(getName() + " замахал руками и принялся грозиться.");
        return stalker;
    }

    public List<Human> rejoice(String event, List<Human> group) {
        feel(Emotion.JOY, event);
        isShouting = false;
        System.out.println("Новички сразу духом воспрянули. Головами вертят, страху в них почти не осталось, одно любопытство да радость.");
        return group;
    }

    public String askQuestion(String question, Human target) {
        String questionText = getName() + " спрашивает: \"" + question + "\"";
        System.out.println(questionText);
        
        if (question.contains("гравитационное")) {
            System.out.println("Про гравитационное поле, про э'комариную плешь' то есть, втолковывает.");
        }
        
        return questionText;
    }

    public Stalker followStalker(Stalker stalker) {
        System.out.println(getName() + " следует за сталкером.");
        return stalker;
    }

    public Galosha boardGalosha(Galosha galosha) {
        System.out.println(getName() + " залезает в Галошу.");
        galosha.board(List.of(this));
        return galosha;
    }

    public boolean isTrained() { return isTrained; }
    public void setTrained(boolean trained) { isTrained = trained; }
    
    public boolean isShouting() { return isShouting; }
    public void setShouting(boolean shouting) { isShouting = shouting; }
}