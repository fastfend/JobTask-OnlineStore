package com.cdprojekt.store.service.game.controller;

import com.cdprojekt.store.service.game.service.GameService;
import com.cdprojekt.store.shared.game.dto.GameDto;
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
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @GetMapping
    public Page<GameDto> find(Pageable page) {
        return gameService
                .find(page);
    }

    @GetMapping("/{gameId}")
    public GameDto get(@PathVariable UUID gameId) {
        return gameService
                .get(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public GameDto create(@RequestBody GameDto game) {
        return gameService
                .create(game);
    }

    @DeleteMapping("/{gameId}")
    public void delete(@PathVariable UUID gameId) {
        if(!gameService.delete(gameId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

