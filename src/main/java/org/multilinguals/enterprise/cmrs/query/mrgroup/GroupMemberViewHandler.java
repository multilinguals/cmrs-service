package org.multilinguals.enterprise.cmrs.query.mrgroup;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupCreatedEvent;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GroupMemberViewHandler {
    private UserDetailsViewRepository userDetailsViewRepository;

    private GroupMemberViewRepository groupMemberViewRepository;

    @Autowired
    public GroupMemberViewHandler(UserDetailsViewRepository userDetailsViewRepository, GroupMemberViewRepository groupMemberViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
        this.groupMemberViewRepository = groupMemberViewRepository;
    }

    @EventHandler
    public void on(MealReservationGroupCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        this.userDetailsViewRepository.findById(event.getOwnerId().getIdentifier()).ifPresent(owner -> {
            GroupMemberView groupMemberView = new GroupMemberView(owner.getId(), event.getId().getIdentifier(), owner.getRealName(), new Date(createdTime.toEpochMilli()));
            this.groupMemberViewRepository.save(groupMemberView);
        });
    }
}
