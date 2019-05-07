package com.bartosektom.letsplayfolks.exception;

public class UnexpectedChallengeException extends Exception {

    private static final long serialVersionUID = -5094498394544324844L;

    public UnexpectedChallengeException(String errorMessage) {
        super(errorMessage);
    }
}
