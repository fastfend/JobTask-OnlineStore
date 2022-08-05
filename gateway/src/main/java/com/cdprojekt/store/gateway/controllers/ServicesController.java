package com.cdprojekt.store.gateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServicesController {
    private final DiscoveryClient discoveryClient;

    @GetMapping()
    private List<List<String>> get() {
        return discoveryClient.getServices()
                .stream()
                .map((s -> discoveryClient.getInstances(s)
                        .stream()
                        .map(Object::toString)
                        .toList()))
                .toList();
    }

}
