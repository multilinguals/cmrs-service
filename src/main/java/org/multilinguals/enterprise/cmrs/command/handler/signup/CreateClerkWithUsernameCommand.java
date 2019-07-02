package org.multilinguals.enterprise.cmrs.command.handler.signup;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;

public class CreateClerkWithUsernameCommand extends AbstractCommand {
    private String username;

    private String realName;

    private String password;

    public CreateClerkWithUsernameCommand(String username, String realName, String password) {
        this.username = username;
        this.realName = realName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getRealName() {
        return realName;
    }

    public String getPassword() {
        return password;
    }
}
