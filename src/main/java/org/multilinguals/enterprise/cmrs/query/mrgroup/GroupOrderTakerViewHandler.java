package org.multilinguals.enterprise.cmrs.query.mrgroup;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupDeletedEvent;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class GroupOrderTakerViewHandler {
    private UserDetailsViewRepository userDetailsViewRepository;
    private GroupOrderTakerViewRepository groupOrderTakerViewRepository;

    @Autowired
    public GroupOrderTakerViewHandler(UserDetailsViewRepository userDetailsViewRepository, GroupOrderTakerViewRepository groupOrderTakerViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
        this.groupOrderTakerViewRepository = groupOrderTakerViewRepository;
    }

    @EventHandler
    public void on(MealReservationGroupCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        this.userDetailsViewRepository.findById(event.getOwnerId().getIdentifier()).ifPresent(owner -> {
            GroupOrderTakerView groupOrderTakerView = new GroupOrderTakerView(owner.getId(), event.getId().getIdentifier(), owner.getRealName(), new Date(createdTime.toEpochMilli()));
            this.groupOrderTakerViewRepository.save(groupOrderTakerView);
        });
    }

    @EventHandler
    public void on(MealReservationGroupDeletedEvent event) {
        List<GroupOrderTakerView> groupOrderTakerViews = this.groupOrderTakerViewRepository.findAll(Example.of(new GroupOrderTakerView(null, event.getId().getIdentifier(), null, null)));

        this.groupOrderTakerViewRepository.deleteAll(groupOrderTakerViews);
    }
}
