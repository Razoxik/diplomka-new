package com.bartosektom.letsplayfolks.exception;

public class UserAlreadyExistException extends Exception {

    private static final long serialVersionUID = 4428971469873011743L;

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
