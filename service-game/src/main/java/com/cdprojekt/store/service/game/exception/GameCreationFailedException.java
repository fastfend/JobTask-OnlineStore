package com.cdprojekt.store.service.game.exception;

public class GameCreationFailedException extends RuntimeException {
    public GameCreationFailedException(String message, Throwable err) {
        super(message, err);
    }
}
