package org.multilinguals.enterprise.cmrs.query.taste;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.event.TasteCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TasteViewHandler {
    private TasteViewRepository tasteViewRepository;

    @Autowired
    public TasteViewHandler(TasteViewRepository tasteViewRepository) {
        this.tasteViewRepository = tasteViewRepository;
    }

    @EventHandler
    public void on(TasteCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        TasteView tasteView = new TasteView(event.getId().getIdentifier(), event.getName(), new Date(createdTime.toEpochMilli()));

        this.tasteViewRepository.save(tasteView);
    }
}
