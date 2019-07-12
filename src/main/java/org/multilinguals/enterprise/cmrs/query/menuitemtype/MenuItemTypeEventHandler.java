package org.multilinguals.enterprise.cmrs.query.menuitemtype;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.event.MenuItemTypeCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MenuItemTypeEventHandler {
    private MenuItemTypeViewRepository menuItemTypeViewRepository;

    @Autowired
    public MenuItemTypeEventHandler(MenuItemTypeViewRepository menuItemTypeViewRepository) {
        this.menuItemTypeViewRepository = menuItemTypeViewRepository;
    }

    @EventHandler
    public void on(MenuItemTypeCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        MenuItemTypeView menuItemTypeView = new MenuItemTypeView(event.getId().getIdentifier(), event.getName(), new Date(createdTime.toEpochMilli()));
        this.menuItemTypeViewRepository.save(menuItemTypeView);
    }
}
