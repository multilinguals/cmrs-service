package org.multilinguals.enterprise.cmrs.command.aggregate.role;

import org.apache.commons.lang3.StringUtils;
import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;
import org.springframework.util.DigestUtils;

public class RoleId extends AbstractAggregateIdentifier {
    private String roleName;

    public RoleId(String roleName) {
        this.roleName = roleName;
        super.setIdentifier(buildIdentifier());
    }

    @Override
    protected String buildIdentifier() {
        if (StringUtils.isEmpty(this.roleName) || this.roleName == null) {
            return null;
        } else {
            return DigestUtils.md5DigestAsHex(this.roleName.getBytes());
        }
    }

    public String getRoleName() {
        return roleName;
    }
}
