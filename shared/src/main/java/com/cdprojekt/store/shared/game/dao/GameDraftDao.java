package com.cdprojekt.store.shared.game.dao;

import com.cdprojekt.store.shared.base.BaseDocument;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document
@Getter
@Setter
public class GameDraftDao extends BaseDocument {
    private UUID gameId;
    private String name;
    private String description;
    private BigDecimal cost;
}