package com.rc.simpleserver.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "file:external-config/endpoints.properties")
@ConfigurationProperties("simpleserver")
@Getter
@Setter
@AllArgsConstructor
public class EndpointsConfig {
    private List<String> endpoint;
}
