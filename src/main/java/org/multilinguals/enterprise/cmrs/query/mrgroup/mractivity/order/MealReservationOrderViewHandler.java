package org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity.order;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrorder.event.MealReservationOrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MealReservationOrderViewHandler {
    private MealReservationOrderDetailsViewRepository mealReservationOrderDetailsViewRepository;

    @Autowired
    public MealReservationOrderViewHandler(MealReservationOrderDetailsViewRepository mealReservationOrderDetailsViewRepository) {
        this.mealReservationOrderDetailsViewRepository = mealReservationOrderDetailsViewRepository;
    }

    @EventHandler
    public void on(MealReservationOrderCreatedEvent event, @Timestamp java.time.Instant eventCreatedTime) {

    }
}
