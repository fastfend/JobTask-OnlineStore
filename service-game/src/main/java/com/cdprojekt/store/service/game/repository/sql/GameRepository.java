package com.cdprojekt.store.service.game.repository.sql;

import com.cdprojekt.store.shared.game.dao.GameDao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepository extends PagingAndSortingRepository<GameDao, UUID> {
}
