package locations;

import humans.Human;
import util.Coordinates;
import util.Emotion;
import util.HealthStatus;

import java.util.List;

public class Cordon extends Location {
    private boolean isGuarded;
    private int guardsCount;

    public Cordon(Coordinates position) {
        super("CORDON-001", "Кордон", position);
        this.isGuarded = true;
        this.guardsCount = 4;
    }

    @Override
    public boolean enter(List<Human> group) {
        System.out.println("Подходим к кордону.");
        return checkPass(group);
    }

    @Override
    public boolean exit(List<Human> group) {
        System.out.println("Отходим от кордона.");
        return true;
    }

    public boolean blockPassage(boolean permanent) {
        this.isGuarded = permanent;
        System.out.println("Проход через кордон " + (permanent ? "заблокирован" : "открыт"));
        return this.isGuarded;
    }

    public void sniperFire(Human target) {
        System.out.println("Патрули-пулеметчики открывают огонь по " + target.getName() + "!");
        target.feel(Emotion.FEAR, "обстрел на кордоне");
        target.setHealth(HealthStatus.TIRED);
    }

    public boolean checkPass(List<Human> group) {
        if (!isGuarded) {
            System.out.println("Кордон не охраняется. Проходим свободно.");
            return true;
        }
        
        System.out.println("На кордоне патрули-пулеметчики. " + guardsCount + " человек.");
        System.out.println("Приходится рылом в землю - молиться до темноты.");
        
        for (Human human : group) {
            human.feel(Emotion.FEAR, "патрули на кордоне");
            human.setHealth(util.HealthStatus.TIRED);
        }
        
        System.out.println("Хабар рядом лежит, и ты даже не знаешь, то ли он просто лежит, то ли он тихонько убивает.");
        
        return false;
    }

    public boolean isGuarded() { return isGuarded; }
    public void setGuarded(boolean guarded) { isGuarded = guarded; }
    public int getGuardsCount() { return guardsCount; }
    public void setGuardsCount(int count) { guardsCount = count; }
}