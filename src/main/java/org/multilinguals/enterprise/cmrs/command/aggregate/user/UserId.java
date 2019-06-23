package org.multilinguals.enterprise.cmrs.command.aggregate.user;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class UserId extends AbstractAggregateIdentifier {
    public UserId() {
        super();
    }

    public UserId(String identifier) {
        super(identifier);
    }
}
