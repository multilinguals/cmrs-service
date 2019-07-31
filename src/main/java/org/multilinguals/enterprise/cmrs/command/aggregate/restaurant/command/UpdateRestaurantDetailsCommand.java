package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;

public class UpdateRestaurantDetailsCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private RestaurantId id;

    private String name;

    private String description;

    public UpdateRestaurantDetailsCommand(RestaurantId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public RestaurantId getId() {
        return id;
    }

    public void setId(RestaurantId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
