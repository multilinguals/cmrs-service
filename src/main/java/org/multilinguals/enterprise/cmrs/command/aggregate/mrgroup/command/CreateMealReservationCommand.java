package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command;

import org.hibernate.validator.constraints.Length;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;

public class CreateMealReservationCommand {
    @NotEmpty
    @Length(min = 4, max = 8)
    private String name;

    @NotEmpty
    private UserId creatorId;

    public CreateMealReservationCommand(@NotEmpty @Length(min = 4, max = 8) String name, @NotEmpty UserId creatorId) {
        this.name = name;
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserId getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UserId creatorId) {
        this.creatorId = creatorId;
    }
}
