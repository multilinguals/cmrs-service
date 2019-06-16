package org.multilinguals.enterprise.cmrs.query.rbac;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.event.RoleCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RoleViewEventHandler {
    private RoleDetailsViewRepository roleDetailsViewRepository;

    @Autowired
    public RoleViewEventHandler(RoleDetailsViewRepository roleDetailsViewRepository) {
        this.roleDetailsViewRepository = roleDetailsViewRepository;
    }

    @EventHandler
    public void on(RoleCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        RoleDetailsView roleDetailsView = new RoleDetailsView(event.getId().getIdentifier(), event.getName(), new Date(createdTime.toEpochMilli()));
        this.roleDetailsViewRepository.save(roleDetailsView);
    }
}
