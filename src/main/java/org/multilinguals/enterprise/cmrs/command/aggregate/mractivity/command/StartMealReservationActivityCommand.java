package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;

public class StartMealReservationActivityCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationActivityId id;

    public StartMealReservationActivityCommand(MealReservationActivityId id) {
        this.id = id;
    }

    public MealReservationActivityId getId() {
        return id;
    }
}
