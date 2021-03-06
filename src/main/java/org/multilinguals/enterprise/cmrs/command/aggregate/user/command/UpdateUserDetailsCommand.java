package org.multilinguals.enterprise.cmrs.command.aggregate.user.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import javax.validation.constraints.NotEmpty;

public class UpdateUserDetailsCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserId id;

    @NotEmpty
    private String realName;

    public UpdateUserDetailsCommand(UserId id, String realName) {
        this.id = id;
        this.realName = realName;
    }

    public UserId getId() {
        return id;
    }

    public void setId(UserId id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
