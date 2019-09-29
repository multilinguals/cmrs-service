package org.multilinguals.enterprise.cmrs.query.mrgroup.member;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMember;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMemberId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.*;
import org.multilinguals.enterprise.cmrs.query.mrgroup.constant.GroupRoles;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.*;

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
        List<GroupMemberView> groupMemberViews = new ArrayList<>();
        for (Map.Entry<GroupMemberId, GroupMember> entry : event.getMembers().entrySet()) {
            GroupMember member = entry.getValue();
            this.userDetailsViewRepository.findById(member.getUserId().getIdentifier()).ifPresent(memberUser -> {
                GroupMemberView groupMemberView = new GroupMemberView(
                        member.getId().getIdentifier(),
                        member.getUserId().getIdentifier(),
                        event.getId().getIdentifier(),
                        memberUser.getRealName(),
                        member.getGroupRoles(),
                        new Date(createdTime.toEpochMilli())
                );

                groupMemberViews.add(groupMemberView);
            });
        }

        this.groupMemberViewRepository.saveAll(groupMemberViews);
    }

    @EventHandler
    public void on(MealReservationGroupDeletedEvent event) {
        List<GroupMemberView> memberViews = this.groupMemberViewRepository.findAll(Example.of(new GroupMemberView(null, event.getId().getIdentifier(), null, null, null)));

        this.groupMemberViewRepository.deleteAll(memberViews);
    }

    @EventHandler
    public void on(MembersAddedToMealReservationGroupEvent event, @Timestamp java.time.Instant createdTime) {
        List<GroupMemberView> groupMemberViews = new ArrayList<>();

        Map<GroupMemberId, GroupMember> newMembers = event.getNewMembers();
        for (Map.Entry<GroupMemberId, GroupMember> entry : newMembers.entrySet()) {
            String memberId = entry.getKey().getIdentifier();
            this.userDetailsViewRepository.findById(entry.getValue().getUserId().getIdentifier()).ifPresent(memberUser -> {
                GroupMemberView groupMemberView = new GroupMemberView(
                        memberId,
                        memberUser.getId(),
                        event.getGroupId().getIdentifier(),
                        memberUser.getRealName(),
                        Collections.singletonList(GroupRoles.GROUP_MEMBER),
                        new Date(createdTime.toEpochMilli())
                );

                groupMemberViews.add(groupMemberView);
            });
        }

        this.groupMemberViewRepository.saveAll(groupMemberViews);
    }

    @EventHandler
    public void on(MembersRemovedFromMealReservationGroupEvent event, @Timestamp java.time.Instant createdTime) {
        for (GroupMemberId removedMemberId : event.getRemovedMemberIdList()) {
            this.groupMemberViewRepository.deleteById(removedMemberId.getIdentifier());
        }
    }

    @EventHandler
    public void on(RolesSetToMemberEvent event, @Timestamp java.time.Instant createdTime) {
        this.groupMemberViewRepository.findById(event.getMemberId().getIdentifier()).ifPresent(groupMemberView -> {
            groupMemberView.getGroupRoles().addAll(event.getGroupRoles());

            this.groupMemberViewRepository.save(groupMemberView);
        });
    }


    @EventHandler
    public void on(MealReservationGroupOwnerTurnOverEvent event, @Timestamp java.time.Instant createdTime) {
        this.groupMemberViewRepository.findById(event.getPastOwnerId().getIdentifier()).ifPresent(pastOwner -> {
            pastOwner.removeGroupRole(GroupRoles.GROUP_OWNER);
            pastOwner.setUpdatedAt(new Date(createdTime.toEpochMilli()));
        });

        this.groupMemberViewRepository.findById(event.getCurrentOwnerId().getIdentifier()).ifPresent(owner -> {
            owner.addGroupRole(GroupRoles.GROUP_OWNER);
            owner.setUpdatedAt(new Date(createdTime.toEpochMilli()));
        });
    }
}
