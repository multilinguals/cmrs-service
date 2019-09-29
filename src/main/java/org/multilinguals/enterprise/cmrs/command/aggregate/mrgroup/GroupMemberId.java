package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class GroupMemberId extends AbstractAggregateIdentifier {
    public GroupMemberId() {
    }

    public GroupMemberId(String identifier) {
        super(identifier);
    }
}
