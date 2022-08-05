package com.cdprojekt.store.service.game.exception;

public class GameDraftCreationFailedException extends RuntimeException {
    public GameDraftCreationFailedException(String message, Throwable err) {
        super(message, err);
    }
}
