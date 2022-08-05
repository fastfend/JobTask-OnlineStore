package com.cdprojekt.store.shared.game.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GameDto {
    private UUID id;
    private UUID draftId;
    private Date modifiedOn;
    private String name;
    private String description;
    private BigDecimal cost;
}
