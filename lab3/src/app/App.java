package app;

import humans.*;
import items.*;
import locations.*;
import transport.*;
import util.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        
        try {
            Stalker narrator = new Stalker("Рассказчик", 35, 10);
            
            Newbie tender = new Newbie("Тендер", 25);
            Newbie kirill = new Newbie("Кирилл", 24);
            List<Human> group = new ArrayList<>(Arrays.asList(narrator, tender, kirill));
            
            Human linden = new Human("Линден", 40, CharacterType.LEGENDARY_STALKER) {
                { setNickname("Коротышка"); setAlive(false); }
                @Override public HealthStatus reactToDanger(ZoneDangerLevel level, String source) { return HealthStatus.ALIVE; }
                @Override public String speak(String phrase, Human listener) { return ""; }
            };
            
            Human iskhar = new Human("Исхак", 38, CharacterType.LEGENDARY_STALKER) {
                { setNickname("Мослатый"); setAlive(false); }
                @Override public HealthStatus reactToDanger(ZoneDangerLevel level, String source) { return HealthStatus.ALIVE; }
                @Override public String speak(String phrase, Human listener) { return ""; }
            };

            Human barbridge = new Human("Барбридж", 36, CharacterType.STALKER) {
                {setNickname("Барбридж");setAlive(true);}
                @Override public HealthStatus reactToDanger(ZoneDangerLevel level, String source) { return HealthStatus.ALIVE; }
                @Override public String speak(String phrase, Human listener) { return ""; }
            };
            
            narrator.remember("Линден-Коротышка", linden);
            narrator.remember("Мослатый Исхак", iskhar);
            
            ItemFactory itemFactory = new ItemFactory();

            Flask flask = (Flask) itemFactory.createFlask(0.4, "крепкий самогон");
            Watch watch = (Watch) itemFactory.createWatch("1", new TimeSpent(9, 30));
            Cigarette cigarette = (Cigarette) itemFactory.createCigarette("2", 3.0);
            SpecialSuit suit = (SpecialSuit) itemFactory.createSpecialSuit("L");
            DummyArtifact dummy = (DummyArtifact) itemFactory.createDummyArtifact(2.5, false, "серебристый");
            
            Zone zone = Zone.getInstance();
            Cordon cordon = new Cordon(new Coordinates(0, 50, 0));
            SanitaryHangar hangar = new SanitaryHangar(new Coordinates(10, 10, 0));
            Galosha galosha = new Galosha(new Coordinates(0, 0, 0), 30);
            //
            narrator.boardGalosha(galosha, group);
            tender.rejoice("начало пути", group);
            kirill.askQuestion("А что такое гравитационное поле?", narrator);
            dummy.use(narrator);

            narrator.tellStoryAbout(linden, group);
            narrator.calmDownNewbies(new ArrayList<>(Arrays.asList(tender, kirill)), true);
            System.out.println("Плывем в тишине, а я об одном думаю: как колпачок свинчивать буду.");
            
            galosha.startAutoReturn();
            galosha.goBack(true);
            narrator.exitZone(zone, group);
            
            narrator.goToSanitaryHangar(hangar, group);
            suit.takeOff();
            System.out.println("Заперся в кабинке, вытащил флягу, отвинтил колпачок и присосался к ней, как клоп.");
            flask.screwCap(false);
            flask.use(narrator);
            
            cigarette.light();
            cigarette.use(narrator);
            flask.use(narrator);

            TimeSpent exitTime = new TimeSpent(14, 35);
            watch.getTimeSpentInZone(new TimeSpent(9, 30), exitTime);

            List<Human> witnesses = new ArrayList<>(Arrays.asList(narrator, barbridge));
            narrator.rememberIskharStory(iskhar, barbridge, cordon, witnesses);
            
        } catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
        }
    }
}