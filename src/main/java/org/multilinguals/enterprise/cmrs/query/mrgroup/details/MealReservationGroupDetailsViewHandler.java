package org.multilinguals.enterprise.cmrs.query.mrgroup.details;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupDeletedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupDetailsUpdatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupOwnerTurnOverEvent;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MealReservationGroupDetailsViewHandler {
    private UserDetailsViewRepository userDetailsViewRepository;

    private MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository;

    @Autowired
    public MealReservationGroupDetailsViewHandler(UserDetailsViewRepository userDetailsViewRepository, MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
        this.mealReservationGroupDetailsRepository = mealReservationGroupDetailsRepository;
    }

    @EventHandler
    public void on(MealReservationGroupCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        this.userDetailsViewRepository.findById(event.getOwnerId().getIdentifier()).ifPresent(creator -> {
            MealReservationGroupDetailsView mealReservationGroupDetailsView = new MealReservationGroupDetailsView(
                    event.getId().getIdentifier(), event.getName(), event.getDescription()
            );

            mealReservationGroupDetailsView.setOwnerId(creator.getId());
            mealReservationGroupDetailsView.setOwnerRealName(creator.getRealName());

            mealReservationGroupDetailsView.setCreatorId(creator.getId());
            mealReservationGroupDetailsView.setCreatorRealName(creator.getRealName());

            mealReservationGroupDetailsView.setCreatedAt(new Date(createdTime.toEpochMilli()));

            this.mealReservationGroupDetailsRepository.save(mealReservationGroupDetailsView);
        });
    }

    @EventHandler
    public void on(MealReservationGroupDetailsUpdatedEvent event, @Timestamp java.time.Instant createdTime) {
        this.mealReservationGroupDetailsRepository.findById(event.getMealReservationGroupId().getIdentifier())
                .ifPresent(mealReservationGroupDetailsView -> {
                    if (event.getName() != null) {
                        mealReservationGroupDetailsView.setName(event.getName());
                    }

                    if (event.getDescription() != null) {
                        mealReservationGroupDetailsView.setDescription(event.getDescription());
                    }

                    mealReservationGroupDetailsView.setUpdatedAt(new Date(createdTime.toEpochMilli()));

                    this.mealReservationGroupDetailsRepository.save(mealReservationGroupDetailsView);
                });
    }

    @EventHandler
    public void on(MealReservationGroupDeletedEvent event) {
        this.mealReservationGroupDetailsRepository.findById(event.getId().getIdentifier())
                .ifPresent(mealReservationGroupDetailsView -> {
                    this.mealReservationGroupDetailsRepository.delete(mealReservationGroupDetailsView);
                });
    }

    @EventHandler
    public void on(MealReservationGroupOwnerTurnOverEvent event, @Timestamp java.time.Instant createdTime) {
        this.mealReservationGroupDetailsRepository.findById(event.getMealReservationGroupId().getIdentifier())
                .ifPresent(mrGroup -> {
                    this.userDetailsViewRepository.findById(event.getCurrentOwnerId().getIdentifier())
                            .ifPresent(currentOwner -> {
                                mrGroup.setOwnerId(currentOwner.getId());
                                mrGroup.setOwnerRealName(currentOwner.getRealName());
                                mrGroup.setUpdatedAt(new Date(createdTime.toEpochMilli()));
                                this.mealReservationGroupDetailsRepository.save(mrGroup);
                            });
                });
    }
}
