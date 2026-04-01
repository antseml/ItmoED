package humans;

import util.*;
import locations.*;
import transport.Galosha;
import interfaces.StalkerSkill;
import java.util.ArrayList;
import java.util.List;

public class Stalker extends Human implements StalkerSkill {
    private int experienceYears;
    private List<Human> memories;
    private List<Human> currentGroup;

    public Stalker(String name, int age, int experienceYears) {
        super(name, age, CharacterType.STALKER);
        this.experienceYears = experienceYears;
        this.memories = new ArrayList<>();
        this.currentGroup = new ArrayList<>();
    }

    @Override
    public HealthStatus reactToDanger(ZoneDangerLevel level, String dangerSource) {
        if (level == ZoneDangerLevel.CRITICAL) {
            setHealth(HealthStatus.TIRED);
            feel(Emotion.FEAR, dangerSource);
            System.out.println("Приходится залечь и ждать.");
        } else if (level == ZoneDangerLevel.UNPREDICTABLE) {
            feel(Emotion.CURIOSITY, dangerSource);
            System.out.println("Зона непредсказуема, надо быть начеку.");
        }
        return getHealth();
    }

    @Override
    public String speak(String phrase, Human listener) {
        String speech = getName() + " говорит " + listener.getName() + ": \"" + phrase + "\"";
        System.out.println(speech);
        return speech;
    }

    @Override
    public boolean detectAnomaly(Coordinates location, String anomalyType) {
        boolean detected = experienceYears > 3;
        if (detected) {
            System.out.println("Чую аномалию. 'Комариная плешь' где-то рядом.");
        }
        return detected;
    }

    @Override
    public Coordinates navigateBlindly(Coordinates startPoint, Coordinates endPoint) {
        System.out.println("Идем вслепую, по памяти...");
        return new Coordinates(
            (startPoint.x() + endPoint.x()) / 2,
            (startPoint.y() + endPoint.y()) / 2,
            (startPoint.z() + endPoint.z()) / 2
        );
    }

    @Override
    public boolean calmDown(Newbie newbie, String method) {
        if (method.equals("threat")) {
            System.out.println("Молчите, говорю, и глядите лучше по сторонам, а то будет с вами как с Линденом-Коротышкой.");
            newbie.feel(Emotion.FEAR, "рассказ о смерти в Зоне");
            return false;
        } else {
            newbie.feel(Emotion.CURIOSITY, "обещание показать что-то интересное");
            return true;
        }
    }

    public List<Newbie> calmDownNewbies(List<Newbie> newbies, boolean useThreats) {
        List<Newbie> calmed = new ArrayList<>();
        for (Newbie newbie : newbies) {
            if (calmDown(newbie, useThreats ? "threat" : "story")) {
                calmed.add(newbie);
            }
        }
        System.out.println("Подействовало. Даже не спросили, что случилось с Линденом-Коротышкой.");
        return calmed;
    }

    public String tellStoryAbout(Human character, List<Human> listeners) {
        String story = "";
        
        if (character.getNickname() != null && character.getNickname().equals("Коротышка")) {
            story = "Сколько дураков на обратном пути с радости гробанулись. В Зоне по знакомой тропке сто раз благополучно пройдешь, а на сто первый гробанешься.";
            System.out.println(story);
        } else if (character.getNickname() != null && character.getNickname().equals("Мослатый")) {
            story = "Мослатый Исхак... Царство ему небесное, хороший был мужик, такие долго не живут.";
            System.out.println(story);
        }
        
        feel(Emotion.GRATITUDE, "воспоминания о прошлом");
        return story;
    }

    public void rememberIskharStory(Human iskhar, Human barbridge, Cordon cordon, List<Human> listeners) {
        System.out.println("Или как Мослатый Исхак застрял на рассвете на открытом месте, сбился с дороги и застрял между двумя канавами - ни вправо, ни влево.");
        cordon.sniperFire(iskhar);
    
        System.out.println("Два часа по нему стреляли, попасть не могли, два часа он мертвым притворялся, а потом не выдержал все-таки, встал во весь рост и пошел прямо на пулемет.");
        iskhar.setHealth(HealthStatus.DEAD);
        iskhar.setAlive(false);
    
        System.out.println("Царство ему небесное, хороший был мужик, такие долго не живут.");
        if (barbridge != null) {
            System.out.println("Мы с Барбриджем в ста шагах от него за камушком лежали, он нас выручил. Не заметили нас.");
            remember("спасение Барбриджем", barbridge);
        }
    
        feel(Emotion.GRATITUDE, "воспоминания об Исхаке");
    }
    
    public boolean remember(String event, Human character) {
        memories.add(character);
        return memories.contains(character);
    }

    public Zone enterZone(Zone zone, List<Human> group) {
        this.currentGroup = group;
        System.out.println("Входим в Зону.");
        zone.enter(group, new Coordinates(0, 0, 0));
        return zone;
    }

    public Zone exitZone(Zone zone, List<Human> group) {
        System.out.println("Выбрались из Зоны.");
        zone.exit(group, new Coordinates(0, 0, 0));
        setHealth(HealthStatus.WET);
        System.out.println("Мокрый я был весь с головы до ног.");
        return zone;
    }

    public Galosha boardGalosha(Galosha galosha, List<Human> group) {
        System.out.println("Вскарабкались на 'галошу' сами.");
        galosha.board(group);
        return galosha;
    }

    public Galosha leaveGalosha(Galosha galosha, List<Human> group) {
        galosha.disembark(group);
        return galosha;
    }

    public SanitaryHangar goToSanitaryHangar(SanitaryHangar hangar, List<Human> group) {
        System.out.println("Загнали нас с 'галошей' вместе в 'вошебойку', в санитарный ангар.");
        hangar.processHuman(group, true);
        return hangar;
    }

    public int getExperienceYears() { return experienceYears; }
    public List<Human> getMemories() { return memories; }
    public List<Human> getCurrentGroup() { return currentGroup; }
}