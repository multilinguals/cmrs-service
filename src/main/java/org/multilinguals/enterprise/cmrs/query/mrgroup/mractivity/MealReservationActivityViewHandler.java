package org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MealReservationActivityViewHandler {
    private MealReservationActivityDetailsViewRepository mealReservationActivityDetailsViewRepository;

    @Autowired
    public MealReservationActivityViewHandler(MealReservationActivityDetailsViewRepository mealReservationActivityDetailsViewRepository) {
        this.mealReservationActivityDetailsViewRepository = mealReservationActivityDetailsViewRepository;
    }

    @EventHandler
    public void on(MealReservationActivityCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        MealReservationActivityDetailsView mealReservationActivityDetailsView = new MealReservationActivityDetailsView(
                event.getId().getIdentifier(),
                event.getGroupId().getIdentifier(),
                event.getStatus().getValue(),
                event.getStartedAt(),
                event.getEndAt(),
                new Date(createdTime.toEpochMilli())
        );

        this.mealReservationActivityDetailsViewRepository.save(mealReservationActivityDetailsView);
    }
}
