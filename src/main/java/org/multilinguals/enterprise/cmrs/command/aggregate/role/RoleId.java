package org.multilinguals.enterprise.cmrs.command.aggregate.role;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.NotNull;

public class RoleId extends AbstractAggregateIdentifier {
    private String name;

    public RoleId(String identifier) {
        super(identifier);
    }

    public RoleId(String identifier, @NotNull String name) {
        super(identifier);
        this.name = name;
    }

    public static RoleId ofName(@NotNull String name) {
        String identifier = DigestUtils.md5DigestAsHex(name.getBytes());
        return new RoleId(identifier, name);
    }

    public void setName(@NotNull String name) {
        Assert.isTrue(this.getIdentifier().equals(DigestUtils.md5DigestAsHex(name.getBytes())), "");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
