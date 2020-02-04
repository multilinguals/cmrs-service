package org.multilinguals.enterprise.cmrs.infrastructure.axon;

import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class CommandAndEventConfig {
    @Resource
    AxonConfiguration configuration;

    @Autowired
    public void configureEventSubscribers(EventProcessingConfigurer configurer) {
        configurer.usingSubscribingEventProcessors();
    }

    @Bean
    SimpleDeadlineManager deadlineManager() {
        return SimpleDeadlineManager.builder()
                .scopeAwareProvider(new ConfigurationScopeAwareProvider(configuration))
                .build();
    }
}
