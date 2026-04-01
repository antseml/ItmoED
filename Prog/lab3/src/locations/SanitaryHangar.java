package locations;

import humans.Human;
import util.Coordinates;
import interfaces.Cleanable;
import interfaces.Transportable;
import java.util.ArrayList;
import java.util.List;

public class SanitaryHangar extends Location {
    private int waterTemperature;
    private boolean isIrradiatorOn;
    private int capacity;
    private List<Human> processedHumans;
    private List<Transportable> processedVehicles;

    public SanitaryHangar(Coordinates position) {
        super("HANGAR-001", "Санитарный ангар (Вошебойка)", position);
        this.waterTemperature = 90;
        this.isIrradiatorOn = true;
        this.capacity = 10;
        this.processedHumans = new ArrayList<>();
        this.processedVehicles = new ArrayList<>();
    }

    @Override
    public boolean enter(List<Human> group) {
        System.out.println("Загоняют в 'вошебойку'.");
        List<Human> processed = processHuman(group, true);
        return !processed.isEmpty();
    }

    @Override
    public boolean exit(List<Human> group) {
        System.out.println("Валяйте, ребята, свободны.");
        return true;
    }

    public Transportable washVehicle(Transportable vehicle, String washProgram) {
        System.out.println("Моют технику в трех кипятках и трех щелоках.");
        processedVehicles.add(vehicle);
        return vehicle;
    }

    public Cleanable decontaminate(Cleanable object, int cycles) {
        System.out.println("Дезактивация объекта. Облучают какой-то сволочью, обсыпают чем-то.");
        
        for (int i = 0; i < cycles; i++) {
            object.irradiate(5, 100);
            object.dry(50);
        }
        
        System.out.println("Снова моют. И так по кругу.");
        return object;
    }

    public List<Human> processHuman(List<Human> group, boolean force) {
        System.out.println("Санитарная обработка:");
        
        for (Human human : group) {
            System.out.println("- " + human.getName() + ": моют в трех кипятках");
            
            human.wash("кипяток", waterTemperature);
            human.irradiate(10, 50);
            human.dry(60);
            
            System.out.println("Облучили, обсыпали, снова вымыли, высушили.");
            
            human.setHealth(util.HealthStatus.DRY);
            processedHumans.add(human);
        }
        
        System.out.println("Все, чистые.");
        return processedHumans;
    }

    public int getWaterTemperature() { return waterTemperature; }
    public boolean isIrradiatorOn() { return isIrradiatorOn; }
    public int getCapacity() { return capacity; }
    public List<Human> getProcessedHumans() { return processedHumans; }
    public List<Transportable> getProcessedVehicles() { return processedVehicles; }
}