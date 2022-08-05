package com.cdprojekt.store.service.game.controller;

import com.cdprojekt.store.service.game.service.GameDraftService;
import com.cdprojekt.store.service.game.service.GameService;
import com.cdprojekt.store.shared.game.dto.GameDraftDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/game/{gameId}/draft")
public class GameDraftController {
    private final GameDraftService draftService;
    private final GameService gameService;

    @GetMapping
    public Page<GameDraftDto> search(@PathVariable UUID gameId, Pageable page) {
        return draftService
                .find(gameId, page);
    }

    @PostMapping
    public GameDraftDto create(@PathVariable UUID gameId, @RequestBody GameDraftDto draft) {
        return draftService
                .create(gameId, draft)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/{draftId}")
    public GameDraftDto get(@PathVariable UUID gameId, @PathVariable UUID draftId) {
        return draftService
                .get(gameId, draftId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{draftId}/apply")
    public void apply(@PathVariable UUID gameId, @PathVariable UUID draftId) {
        gameService
                .applyDraft(gameId, draftId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

