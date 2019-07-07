package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotNull;

public class CreateRestaurantCommand extends AbstractCommand {
    @NotNull
    private String name;

    private String description;

    @NotNull
    private UserId creatorId;

    public CreateRestaurantCommand(@NotNull String name, String description, @NotNull UserId creatorId) {
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
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

    public void setCreatorId(UserId creatorId) {
        this.creatorId = creatorId;
    }
}
