package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;

public class MenuItemId extends AbstractAggregateIdentifier {
    public MenuItemId() {
    }

    public MenuItemId(String identifier) {
        super(identifier);
    }
}
