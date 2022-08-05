package com.cdprojekt.store.service.game.repository.mongo;

import com.cdprojekt.store.shared.game.dao.GameDraftDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface GameDraftRepository extends MongoRepository<GameDraftDao, UUID> {
    Page<GameDraftDao> findByGameId(UUID gameId, Pageable pageable);
    Optional<GameDraftDao> getByGameIdAndId(UUID gameId, UUID id);
}
