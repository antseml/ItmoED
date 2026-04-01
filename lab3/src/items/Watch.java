package items;

import humans.Human;
import util.TimeSpent;

public class Watch extends Item {
    private TimeSpent currentTime;
    private String brand;

    public Watch(String brand, TimeSpent initialTime) {
        super("Часы", 0.1);
        this.brand = brand;
        this.currentTime = initialTime;
    }

    @Override
    public Human use(Human user) {
        System.out.println(user.getName() + " смотрит на часы.");
        
        if (currentTime.hours() >= 14 && currentTime.hours() <= 17) {
            System.out.println("Снял часы, смотрит - время после полудня.");
        } else {
            System.out.println("Снял часы, смотрит - " + currentTime.hours() + ":" + 
                              String.format("%02d", currentTime.minutes()));
        }
        
        return user;
    }

    public TimeSpent getTimeSpentInZone(TimeSpent entryTime, TimeSpent exitTime) {
        int hoursDiff = exitTime.hours() - entryTime.hours();
        int minutesDiff = exitTime.minutes() - entryTime.minutes();
        
        if (minutesDiff < 0) {
            hoursDiff--;
            minutesDiff += 60;
        }
        
        TimeSpent spent = new TimeSpent(hoursDiff, minutesDiff);
        
        System.out.println("Снял часы, смотрю - а в Зоне-то мы пробыли " + 
                          hoursDiff + " часов " + minutesDiff + " минут, господа мои.");
        
        if (hoursDiff < 10) {
            System.out.println(hoursDiff + " часов. Меня аж передернуло.");
        }
        
        System.out.println("Да, господа мои, в Зоне времени нет. " + hoursDiff + " часов. А если разобраться, что такое для сталкера " + hoursDiff + " часов? Плюнуть и растереть.");
        
        return spent;
    }

    public Watch setTime(TimeSpent newTime) {
        this.currentTime = newTime;
        return this;
    }


    public TimeSpent getCurrentTime() { return currentTime; }
    public String getBrand() { return brand; }
}