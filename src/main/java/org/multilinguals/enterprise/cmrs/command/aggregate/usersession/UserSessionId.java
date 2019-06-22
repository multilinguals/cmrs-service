package org.multilinguals.enterprise.cmrs.command.aggregate.usersession;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class UserSessionId extends AbstractAggregateIdentifier {
    public UserSessionId() {
        super();
    }

    public UserSessionId(String identifier) {
        super(identifier);
    }
}
