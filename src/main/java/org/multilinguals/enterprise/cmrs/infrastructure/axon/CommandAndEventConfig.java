package org.multilinguals.enterprise.cmrs.infrastructure.axon;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandAndEventConfig {
    @Autowired
    public void configureEventSubscribers(EventProcessingConfigurer configurer) {
        configurer.usingSubscribingEventProcessors();
    }
}
