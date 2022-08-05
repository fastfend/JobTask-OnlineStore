package com.cdprojekt.store.shared.game.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GameDraftDto {
    private UUID id;
    private UUID gameId;
    private String name;
    private String description;
    private BigDecimal cost;
}
