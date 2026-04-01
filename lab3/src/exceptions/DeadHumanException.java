package exceptions;

public class DeadHumanException extends Exception {
    public DeadHumanException() {
        super("Мертвый не может взаимодействовать с предметами");
    }
    
    public DeadHumanException(String message) {
        super(message);
    }
    
    @Override
    public String getMessage() {
        return "DeadHumanException: " + super.getMessage();
    }
}