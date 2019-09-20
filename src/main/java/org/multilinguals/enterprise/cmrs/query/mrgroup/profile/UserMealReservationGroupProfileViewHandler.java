package org.multilinguals.enterprise.cmrs.query.mrgroup.profile;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupCreatedEvent;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMealReservationGroupProfileViewHandler {
    private UserDetailsViewRepository userDetailsViewRepository;
    private UserMealReservationGroupProfileRepository userMealReservationGroupProfileRepository;

    @Autowired
    public UserMealReservationGroupProfileViewHandler(UserDetailsViewRepository userDetailsViewRepository, UserMealReservationGroupProfileRepository userMealReservationGroupProfileRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
        this.userMealReservationGroupProfileRepository = userMealReservationGroupProfileRepository;
    }

    @EventHandler
    public void on(MealReservationGroupCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        this.userDetailsViewRepository.findById(event.getCreatorId().getIdentifier()).ifPresent(creator -> {
            UserMealReservationGroupProfileView userMealReservationGroupProfileView = new UserMealReservationGroupProfileView(
                    creator.getId(),
                    event.getId().getIdentifier(),
                    event.getName(),
                    creator.getId(),
                    creator.getRealName(),
                    new Date(createdTime.toEpochMilli())
            );

            this.userMealReservationGroupProfileRepository.save(userMealReservationGroupProfileView);
        });
    }
}
