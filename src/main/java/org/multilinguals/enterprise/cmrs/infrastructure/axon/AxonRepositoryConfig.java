package org.multilinguals.enterprise.cmrs.infrastructure.axon;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.Account;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishType;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemType;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivity;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroup;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.UserPassword;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.Restaurant;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.Role;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.Taste;
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

    @Bean()
    public Repository<Restaurant> restaurantAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(Restaurant.class).eventStore(eventStore).build();
    }

    @Bean()
    public Repository<MenuItemType> menuItemTypeAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(MenuItemType.class).eventStore(eventStore).build();
    }

    @Bean()
    public Repository<DishType> dishTypeAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(DishType.class).eventStore(eventStore).build();
    }

    @Bean()
    public Repository<Taste> tasteAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(Taste.class).eventStore(eventStore).build();
    }

    @Bean()
    public Repository<MealReservationGroup> mrGroupAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(MealReservationGroup.class).eventStore(eventStore).build();
    }

    @Bean()
    public Repository<MealReservationActivity> mrActivityAggregateRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(MealReservationActivity.class).eventStore(eventStore).build();
    }

    @Bean
    public MongoSagaStore sagaRepository(org.axonframework.extensions.mongo.MongoTemplate axonMongoTemplate) {
        return MongoSagaStore.builder().mongoTemplate(axonMongoTemplate).build();
    }
}
