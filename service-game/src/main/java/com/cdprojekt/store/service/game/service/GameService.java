package com.cdprojekt.store.service.game.service;

import com.cdprojekt.store.service.game.exception.GameCreationFailedException;
import com.cdprojekt.store.service.game.exception.GameUpdateFailedException;
import com.cdprojekt.store.service.game.repository.mongo.GameDraftRepository;
import com.cdprojekt.store.service.game.repository.sql.GameRepository;
import com.cdprojekt.store.shared.game.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GameDraftRepository draftRepository;

    private final GameDtoService dtoService;

    public GameDto create(GameDto game) {
        if(game == null) throw new NullPointerException("Game is null");

        try {
            return dtoService.toDto(gameRepository.save(dtoService.toDao(game)));
        } catch (Exception ex) {
            throw new GameCreationFailedException("Failed to create game", ex);
        }
    }

    public Optional<GameDto> get(UUID gameId) {
        return gameRepository
                .findById(gameId)
                .map(dtoService::toDto);
    }

    public Page<GameDto> find(Pageable page) {
        return gameRepository
                .findAll(page)
                .map(dtoService::toDto);
    }

    public Optional<GameDto> applyDraft(UUID gameId, UUID draftId) {
        var game = gameRepository
                .findById(gameId)
                .orElseThrow(() -> new GameUpdateFailedException("Game not found"));

        if (game.getDraftId() != null && game.getDraftId().equals(draftId)) {
            throw new GameUpdateFailedException("Draft already selected");
        }

        var draft = draftRepository
                .findById(draftId)
                .orElseThrow(() -> new GameUpdateFailedException("Draft not found"));

        if(!draft.getGameId().equals(gameId)) {
            throw new GameUpdateFailedException("Game draft mismatch");
        }

        game.setDraftId(draft.getId());
        game.setName(draft.getName());
        game.setDescription(draft.getDescription());
        game.setDraftId(draft.getId());
        game.setCost(draft.getCost());
        game.setModifiedOn(new Date());

        return Optional.ofNullable(dtoService.toDto(gameRepository.save(game)));
    }

    public boolean delete(UUID gameId) {
        if(!gameRepository.existsById(gameId)) {
            return false;
        }

        gameRepository.deleteById(gameId);
        return true;
    }
}
