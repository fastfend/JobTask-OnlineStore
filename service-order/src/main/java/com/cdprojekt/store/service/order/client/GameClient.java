package com.cdprojekt.store.service.order.client;

import com.cdprojekt.store.shared.game.dto.GameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "service-game", url="${microservices.game}")
public interface GameClient {
    @GetMapping(value = "/game/{gameId}")
    GameDto get(@PathVariable(value = "gameId") UUID gameId);
}
