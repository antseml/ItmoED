package items;

import humans.Human;
import util.Emotion;

public class DummyArtifact extends Item {
    private double gravitationalPull;
    private boolean isDangerous;
    private String color;

    public DummyArtifact(double gravitationalPull, boolean isDangerous, String color) {
        super("Пустышка", 2.0);
        this.gravitationalPull = gravitationalPull;
        this.isDangerous = isDangerous;
        this.color = color;
    }

    @Override
    public Human use(Human user) {
        System.out.println(user.getName() + " рассматривает артефакт. Цвет: " + color);
        System.out.println("Гравитационное поле: " + gravitationalPull + " ед.");
        
        if (isDangerous) {
            System.out.println("Артефакт опасен! Может тихонько убивать.");
            user.feel(Emotion.FEAR, "опасный артефакт");
        } else {
            System.out.println("Артефакт безопасен. 'Пустышка'.");
            user.feel(Emotion.CURIOSITY, "артефакт");
        }
        
        return user;
    }

    public Human emitField(Human affected, int radius) {
        System.out.println(affected.getName() + " ощущает гравитационное поле в радиусе " + radius + " метров.");
        return affected;
    }

    public double getGravitationalPull() { return gravitationalPull; }
    public boolean isDangerous() { return isDangerous; }
    public String getColor() { return color; }
}