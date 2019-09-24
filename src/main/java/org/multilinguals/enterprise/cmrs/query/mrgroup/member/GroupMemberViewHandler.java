package org.multilinguals.enterprise.cmrs.query.mrgroup.member;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupDeletedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupOwnerTurnOverEvent;
import org.multilinguals.enterprise.cmrs.query.mrgroup.constant.GroupRoles;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
            GroupMemberView groupMemberView = new GroupMemberView(
                    owner.getId(),
                    event.getId().getIdentifier(),
                    owner.getRealName(),
                    GroupRoles.GROUP_OWNER,
                    new Date(createdTime.toEpochMilli())
            );
            this.groupMemberViewRepository.save(groupMemberView);
        });
    }

    @EventHandler
    public void on(MealReservationGroupDeletedEvent event) {
        List<GroupMemberView> memberViews = this.groupMemberViewRepository.findAll(Example.of(new GroupMemberView(null, event.getId().getIdentifier(), null, null, null)));

        this.groupMemberViewRepository.deleteAll(memberViews);
    }

    @EventHandler
    public void on(MealReservationGroupOwnerTurnOverEvent event, @Timestamp java.time.Instant createdTime) {
        this.groupMemberViewRepository.findById(event.getPastOwnerId().getIdentifier()).ifPresent(pastOwner -> {
            pastOwner.setGroupRole(GroupRoles.GROUP_ORDER_TAKER);
            pastOwner.setUpdatedAt(new Date(createdTime.toEpochMilli()));
        });

        this.groupMemberViewRepository.findById(event.getCurrentOwnerId().getIdentifier()).ifPresent(owner -> {
            owner.setGroupRole(GroupRoles.GROUP_OWNER);
            owner.setUpdatedAt(new Date(createdTime.toEpochMilli()));
        });
    }
}
