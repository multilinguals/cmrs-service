package org.multilinguals.enterprise.cmrs.command.aggregate.mrorder;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class MealReservationOrderId extends AbstractAggregateIdentifier {
    public MealReservationOrderId() {
    }

    public MealReservationOrderId(String identifier) {
        super(identifier);
    }
}
