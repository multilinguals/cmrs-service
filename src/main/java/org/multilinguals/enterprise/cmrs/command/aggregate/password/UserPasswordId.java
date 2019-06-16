package org.multilinguals.enterprise.cmrs.command.aggregate.password;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class UserPasswordId extends AbstractAggregateIdentifier {
    public UserPasswordId() {
        super();
    }

    public UserPasswordId(String identifier) {
        super(identifier);
    }
}
