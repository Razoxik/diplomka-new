package cz.upce.diplomovaprace.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }


}
