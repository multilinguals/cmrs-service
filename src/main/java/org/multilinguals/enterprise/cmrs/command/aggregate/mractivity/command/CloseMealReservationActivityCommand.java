package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;

public class CloseMealReservationActivityCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    public CloseMealReservationActivityCommand(MealReservationActivityId id) {
        this.id = id;
    }

    public MealReservationActivityId getId() {
        return id;
    }
}
