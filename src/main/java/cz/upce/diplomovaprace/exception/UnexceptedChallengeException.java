package cz.upce.diplomovaprace.exception;

public class UnexceptedChallengeException extends Exception {

    private static final long serialVersionUID = -5094498394544324844L;

    public UnexceptedChallengeException() {
        super();
    }

    public UnexceptedChallengeException(String errorMessage) {
        super(errorMessage);
    }
}
