package org.multilinguals.enterprise.cmrs.command.handler.password;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

public class UpdateSelfPasswordCommand extends AbstractCommand {
    private UserId id;

    private String oldUserPassword;

    private String newUserPassword;

    public UpdateSelfPasswordCommand(UserId id, String oldUserPassword, String newUserPassword) {
        this.id = id;
        this.oldUserPassword = oldUserPassword;
        this.newUserPassword = newUserPassword;
    }

    public UserId getId() {
        return id;
    }

    public String getOldUserPassword() {
        return oldUserPassword;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }
}
