package org.multilinguals.enterprise.cmrs.infrastructure.axon;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
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
        return EventSourcingRepository.builder(Account.class).eventStore(eventStore).build();
    }

    @Bean()
    public Repository<User> userAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(User.class).eventStore(eventStore).build();
    }

    @Bean()
    public Repository<UserPassword> userPasswordAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(UserPassword.class).eventStore(eventStore).build();
    }

    @Bean()
    public Repository<Role> roleAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(Role.class).eventStore(eventStore).build();
    }
}
