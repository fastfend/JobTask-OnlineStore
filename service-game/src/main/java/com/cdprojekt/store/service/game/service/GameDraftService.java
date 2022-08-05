package com.cdprojekt.store.service.game.service;

import com.cdprojekt.store.service.game.exception.GameDraftCreationFailedException;
import com.cdprojekt.store.service.game.repository.mongo.GameDraftRepository;
import com.cdprojekt.store.service.game.repository.sql.GameRepository;
import com.cdprojekt.store.shared.game.dto.GameDraftDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameDraftService {
    private final GameRepository gameRepository;
    private final GameDraftRepository gameDraftRepository;
    private final GameDtoService dtoService;

    public Optional<GameDraftDto> get(UUID gameId, UUID draftId) {
        if(!gameRepository.existsById(gameId)) return Optional.empty();

        return gameDraftRepository
                .getByGameIdAndId(gameId, draftId)
                .map(dtoService::toDto);
    }

    public Page<GameDraftDto> find(UUID gameId, Pageable page) {
        return gameDraftRepository
                .findByGameId(gameId, page)
                .map(dtoService::toDto);
    }

    public Optional<GameDraftDto> create(@NonNull UUID gameId, GameDraftDto draft) {
        if(draft == null) throw new NullPointerException("Game is null");
        if(!gameRepository.existsById(gameId)) return Optional.empty();

        draft.setGameId(gameId);

        try {
            return Optional.of(dtoService.toDto(gameDraftRepository.save(dtoService.toDao(draft))));
        } catch (Exception ex) {
            throw new GameDraftCreationFailedException("Failed to create game draft", ex);
        }
    }
}
