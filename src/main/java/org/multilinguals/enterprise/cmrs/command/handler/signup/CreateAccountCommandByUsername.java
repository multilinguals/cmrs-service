package org.multilinguals.enterprise.cmrs.command.handler.signup;

import org.multilinguals.enterprise.cmrs.command.AbstractCommand;

public class CreateAccountCommandByUsername extends AbstractCommand {
    private String username;

    private String realName;

    private String password;

    private String roleName;

    public CreateAccountCommandByUsername(String username, String realName, String password, String roleName) {
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }
}
