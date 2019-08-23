package org.multilinguals.enterprise.cmrs.query.mrgroup;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupCreatedEvent;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MealReservationGroupEventHandler {
    private UserDetailsViewRepository userDetailsViewRepository;

    private MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository;

    private GroupOrderTakerViewRepository groupOrderTakerViewRepository;

    private GroupMemberViewRepository groupMemberViewRepository;

    @Autowired
    public MealReservationGroupEventHandler(UserDetailsViewRepository userDetailsViewRepository, MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository, GroupOrderTakerViewRepository groupOrderTakerViewRepository, GroupMemberViewRepository groupMemberViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
        this.mealReservationGroupDetailsRepository = mealReservationGroupDetailsRepository;
        this.groupOrderTakerViewRepository = groupOrderTakerViewRepository;
        this.groupMemberViewRepository = groupMemberViewRepository;
    }

    @EventHandler
    public void on(MealReservationGroupCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        this.userDetailsViewRepository.findById(event.getOwnerId().getIdentifier()).ifPresent(owner -> {
            MealReservationGroupDetailsView mealReservationGroupDetailsView = new MealReservationGroupDetailsView(
                    event.getId().getIdentifier(), event.getName(), event.getDescription()
            );

            mealReservationGroupDetailsView.setOwnerId(owner.getId());
            mealReservationGroupDetailsView.setOwnerRealName(owner.getRealName());

            mealReservationGroupDetailsView.setCreatorId(owner.getId());
            mealReservationGroupDetailsView.setCreatorRealName(owner.getRealName());

            this.mealReservationGroupDetailsRepository.save(mealReservationGroupDetailsView);

            GroupOrderTakerView groupOrderTakerView = new GroupOrderTakerView(owner.getId(), event.getId().getIdentifier(), owner.getRealName(), new Date(createdTime.toEpochMilli()));
            this.groupOrderTakerViewRepository.save(groupOrderTakerView);

            GroupMemberView groupMemberView = new GroupMemberView(owner.getId(), event.getId().getIdentifier(), owner.getRealName(), new Date(createdTime.toEpochMilli()));
            this.groupMemberViewRepository.save(groupMemberView);
        });
    }
}
