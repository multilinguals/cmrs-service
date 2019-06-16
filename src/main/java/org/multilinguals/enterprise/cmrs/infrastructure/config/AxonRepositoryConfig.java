package org.multilinguals.enterprise.cmrs.infrastructure.config;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.Account;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPassword;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.Role;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonRepositoryConfig {
    @Bean()
    public Repository<Account> accountAggregateRepository(EventStore eventStore) {
        return new EventSourcingRepository<>(Account.class, eventStore);
    }

    @Bean()
    public Repository<User> userAggregateRepository(EventStore eventStore) {
        return new EventSourcingRepository<>(User.class, eventStore);
    }

    @Bean()
    public Repository<UserPassword> userPasswordRepositoryAggregateRepository(EventStore eventStore) {
        return new EventSourcingRepository<>(UserPassword.class, eventStore);
    }

    @Bean()
    public Repository<Role> roleRepositoryAggregateRepository(EventStore eventStore) {
        return new EventSourcingRepository<>(Role.class, eventStore);
    }
}
