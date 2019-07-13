package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class RestaurantId extends AbstractAggregateIdentifier {
    public RestaurantId() {
    }

    public RestaurantId(String identifier) {
        super(identifier);
    }
}
