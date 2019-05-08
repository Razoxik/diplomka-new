package com.bartosektom.letsplayfolks.exception;

public class GameAlreadyExistException extends Exception {

    private static final long serialVersionUID = -639705042329700324L;

    public GameAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}