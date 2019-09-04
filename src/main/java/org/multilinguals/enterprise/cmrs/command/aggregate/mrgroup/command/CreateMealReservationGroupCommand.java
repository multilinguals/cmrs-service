package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command;

import org.hibernate.validator.constraints.Length;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateMealReservationGroupCommand extends AbstractCommand {
    @NotEmpty
    @Length(min = 4, max = 8)
    private String name;

    @Length(min = 5, max = 200)
    private String description;

    @NotNull
    private UserId creatorId;

    public CreateMealReservationGroupCommand(String name, String description, UserId creatorId) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
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

    public UserId getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UserId creatorId) {
        this.creatorId = creatorId;
    }
}
