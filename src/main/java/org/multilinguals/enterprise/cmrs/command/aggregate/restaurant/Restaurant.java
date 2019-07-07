package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.CreateRestaurantCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.RestaurantCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Restaurant {
    @AggregateIdentifier
    private RestaurantId id;

    private String name;

    private String description;

    private UserId creatorId;

    @AggregateMember
    private List<MenuItem> menuItems;

    protected Restaurant() {
    }

    @CommandHandler
    public Restaurant(CreateRestaurantCommand command) {
        apply(new RestaurantCreatedEvent(new RestaurantId(), command.getName(), command.getDescription(), command.getCreatorId()));
    }

    @EventSourcingHandler
    public void handler(RestaurantCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.creatorId = event.getCreatorId();
    }

    public RestaurantId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserId getCreatorId() {
        return creatorId;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
