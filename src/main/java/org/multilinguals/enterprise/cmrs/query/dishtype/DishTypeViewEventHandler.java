package org.multilinguals.enterprise.cmrs.query.dishtype;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.event.DishTypeCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DishTypeViewEventHandler {
    private final DishTypeViewRepository dishTypeViewRepository;

    public DishTypeViewEventHandler(DishTypeViewRepository dishTypeViewRepository) {
        this.dishTypeViewRepository = dishTypeViewRepository;
    }

    @EventHandler
    public void on(DishTypeCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        DishTypeView dishTypeView = new DishTypeView(event.getId().getIdentifier(), event.getName(), new Date(createdTime.toEpochMilli()));
        this.dishTypeViewRepository.save(dishTypeView);
    }
}
