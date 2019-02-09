package cz.upce.diplomovaprace.exception;

public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = 803641709170749736L;

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
