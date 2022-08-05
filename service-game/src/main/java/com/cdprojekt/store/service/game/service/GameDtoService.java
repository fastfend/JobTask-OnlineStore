package com.cdprojekt.store.service.game.service;

import com.cdprojekt.store.shared.game.dao.GameDao;
import com.cdprojekt.store.shared.game.dao.GameDraftDao;
import com.cdprojekt.store.shared.game.dto.GameDraftDto;
import com.cdprojekt.store.shared.game.dto.GameDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class GameDtoService {
    private final ModelMapper modelMapper = new ModelMapper();

    public GameDtoService() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public GameDto toDto(GameDao dao) {
        return modelMapper.map(dao, GameDto.class);
    }
    public GameDraftDto toDto(GameDraftDao dao) {
        return modelMapper.map(dao, GameDraftDto.class);
    }

    public GameDao toDao(GameDto dto) {
        return modelMapper.map(dto, GameDao.class);
    }
    public GameDraftDao toDao(GameDraftDto dto) {
        return modelMapper.map(dto, GameDraftDao.class);
    }
}
