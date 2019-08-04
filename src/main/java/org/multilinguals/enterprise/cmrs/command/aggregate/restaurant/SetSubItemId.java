package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class SetSubItemId extends AbstractAggregateIdentifier {
    public SetSubItemId() {
    }

    public SetSubItemId(String identifier) {
        super(identifier);
    }
}
