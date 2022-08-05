package com.cdprojekt.store.gateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RoutesController {
    private final RouteDefinitionLocator locator;

    @GetMapping()
    private List<String> get() {
        return locator
                .getRouteDefinitions()
                .filter(routeDefinition -> !routeDefinition.getId().startsWith("ReactiveCompositeDiscoveryClient_"))
                .map(Objects::toString)
                .toStream()
                .toList();
    }

}
