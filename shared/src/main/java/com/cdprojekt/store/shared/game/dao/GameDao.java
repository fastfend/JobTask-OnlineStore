package com.cdprojekt.store.shared.game.dao;

import com.cdprojekt.store.shared.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class GameDao extends BaseEntity {
    private UUID draftId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    private String name;
    private String description;
    private BigDecimal cost;
}
