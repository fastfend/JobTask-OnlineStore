package com.cdprojekt.store.service.game.exception;

public class GameUpdateFailedException extends RuntimeException {
    public GameUpdateFailedException(String message, Throwable err) {
        super(message, err);
    }

    public GameUpdateFailedException(String message) {
        super(message);
    }
}
