package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.Length;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;

public class UpdateMealReservationGroupDetailsCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private MealReservationGroupId id;

    @Length(min = 4, max = 8)
    private String name;

    @Length(min = 5, max = 200)
    private String description;

    public UpdateMealReservationGroupDetailsCommand(MealReservationGroupId id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public MealReservationGroupId getId() {
        return id;
    }

    public void setId(MealReservationGroupId id) {
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
