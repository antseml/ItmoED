package exceptions;

public class EmptyResourceException extends Exception {
    public EmptyResourceException() {
        super("Ресурс исчерпан");
    }
    
    public EmptyResourceException(String message) {
        super(message);
    }
    
    @Override
    public String getMessage() {
        return "EmptyResourceException: " + super.getMessage();
    }
}