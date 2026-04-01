package interfaces;

public interface Cleanable {
    boolean wash(String waterType, int temperature);
    boolean irradiate(int duration, int intensity);
    boolean dry(int airTemperature);
}