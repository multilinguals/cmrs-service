package org.multilinguals.enterprise.cmrs.infrastructure.config;

import com.mongodb.MongoClient;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.StorageStrategy;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.inject.Inject;

@Configuration
@EnableMongoRepositories(basePackages = "org.multilinguals.enterprise.cmrs.query")
public class StorageConfig {
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Inject
    public void configure(SimpleCommandBus simpleCommandBus) {
        simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Bean
    public MongoClient mongo(@Value("${spring.data.mongodb.host}") String mongoHost,
                             @Value("${spring.data.mongodb.port}") int mongoPort) {
        return new MongoClient(mongoHost, mongoPort);
    }

    @Bean
    public MongoTemplate axonMongoTemplate(MongoClient client) {
        return DefaultMongoTemplate.builder().mongoDatabase(client, this.mongoDatabase).build();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(MongoTemplate axonTemplate) {
        return MongoEventStorageEngine.builder().mongoTemplate(axonTemplate).build();
    }

    @Bean
    public MongoSagaStore sagaRepository(MongoTemplate axonTemplate) {
        return MongoSagaStore.builder().mongoTemplate(axonTemplate).build();
    }

}
