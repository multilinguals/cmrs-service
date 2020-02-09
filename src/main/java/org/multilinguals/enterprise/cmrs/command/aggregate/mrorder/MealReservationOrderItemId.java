package org.multilinguals.enterprise.cmrs.command.aggregate.mrorder;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class MealReservationOrderItemId extends AbstractAggregateIdentifier {
    public MealReservationOrderItemId() {
    }

    public MealReservationOrderItemId(String identifier) {
        super(identifier);
    }
}