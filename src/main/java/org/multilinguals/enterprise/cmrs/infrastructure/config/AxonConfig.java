package org.multilinguals.enterprise.cmrs.infrastructure.config;

import com.mongodb.MongoClient;
import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.StorageStrategy;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.documentpercommit.DocumentPerCommitStorageStrategy;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

//    @Bean
//    public EventStorageEngine storageEngine(MongoClient client) {
//        return MongoEventStorageEngine.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build();
//    }

    @Bean
    public StorageStrategy storageStrategy() {
        return new DocumentPerCommitStorageStrategy();
    }

    @Bean
    public MongoTemplate axonMongoTemplate(MongoClient client) {
        return DefaultMongoTemplate.builder().mongoDatabase(client, this.mongoDatabase).build();
    }

    @Bean
    public MongoEventStorageEngine eventStorageEngine(MongoTemplate axonTemplate) {
        return MongoEventStorageEngine.builder().mongoTemplate(axonTemplate).build();
    }

    @Bean
    public MongoSagaStore sagaRepository(MongoTemplate axonTemplate) {
        return MongoSagaStore.builder().mongoTemplate(axonTemplate).build();
    }

    @Bean
    SimpleDeadlineManager deadlineManager(AxonConfiguration configuration) {
        return SimpleDeadlineManager.builder().scopeAwareProvider(new ConfigurationScopeAwareProvider(configuration)).build();
    }
}
