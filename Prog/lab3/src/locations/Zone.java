package locations;

import humans.Human;
import util.*;
import java.util.ArrayList;
import java.util.List;

public class Zone extends Location {
    private static Zone instance;
    private ZoneDangerLevel dangerLevel;
    private List<Coordinates> anomalies;
    private List<Human> visitors;

    private Zone() {
        super("ZONE-001", "Зона", new Coordinates(100, 100, 0));
        this.dangerLevel = ZoneDangerLevel.UNPREDICTABLE;
        this.anomalies = new ArrayList<>();
        this.visitors = new ArrayList<>();
        
        anomalies.add(new Coordinates(120, 100, 0));
        anomalies.add(new Coordinates(80, 120, 3));
        anomalies.add(new Coordinates(90, 90, 5));
    }

    public static Zone getInstance() {
        if (instance == null) {
            instance = new Zone();
        }
        return instance;
    }

    @Override
    public boolean enter(List<Human> group) {
        return enter(group, new Coordinates(0, 0, 0));
    }

    public boolean enter(List<Human> group, Coordinates entryPoint) {
        System.out.println("Входим в Зону. Тяжело только в Зону ходить...");
        
        for (Human human : group) {
            if (!visitors.contains(human)) {
                visitors.add(human);
                
                if (dangerLevel == ZoneDangerLevel.CRITICAL) {
                    human.reactToDanger(dangerLevel, "вход в Зону");
                    System.out.println(human.getName() + " сразу чувствует опасность.");
                } else if (dangerLevel == ZoneDangerLevel.UNPREDICTABLE) {
                    System.out.println(human.getName() + " насторожен. Зона непредсказуема.");
                }
            }
        }
        return true;
    }

    @Override
    public boolean exit(List<Human> group) {
        return exit(group, new Coordinates(0, 0, 0));
    }

    public boolean exit(List<Human> group, Coordinates exitPoint) {
        System.out.println("Выбираемся из Зоны.");
        
        for (Human human : group) {
            visitors.remove(human);
            
            if (human.getHealth() == HealthStatus.ALIVE) {
                System.out.println(human.getName() + ": Живой! Отпустила Зона. Отпустила, сука. Стерва родимая. Подлая.");
            }
        }
        return true;
    }

    public double getRadiationLevel(Coordinates position) {
        for (Coordinates anomaly : anomalies) {
            int dx = Math.abs(position.x() - anomaly.x());
            int dy = Math.abs(position.y() - anomaly.y());
            if (dx < 10 && dy < 10) {
                System.out.println("Высокий уровень радиации! Надо бы провериться.");
                return 50.0;
            }
        }
        return 5.0;
    }

    public HealthStatus affectHuman(Human human) {
        System.out.println("Зона воздействует на " + human.getName());
        
        if (dangerLevel == ZoneDangerLevel.CRITICAL) {
            human.setHealth(HealthStatus.TIRED);
            human.feel(Emotion.DESPAIR, "воздействие Зоны");
            System.out.println("Целый день в Зоне лежишь рылом в землю и уже не молишься даже, а вроде бы бредишь и сам не знаешь, живой ты или мертвый.");
        }
        
        return human.getHealth();
    }

    public void setDangerLevel(ZoneDangerLevel level) {
        this.dangerLevel = level;
        System.out.println("Уровень опасности Зоны изменился на " + level);
    }

    public ZoneDangerLevel getDangerLevel() { return dangerLevel; }
    public List<Coordinates> getAnomalies() { return anomalies; }
    public List<Human> getVisitors() { return visitors; }
}