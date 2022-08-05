package com.cdprojekt.store.service.order.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "microservices", ignoreUnknownFields = false)
@Getter
@Setter
public class FeignConfiguration {
    private String game = "";
}