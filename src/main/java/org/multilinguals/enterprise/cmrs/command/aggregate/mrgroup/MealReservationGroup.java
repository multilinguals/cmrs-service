package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.CreateMealReservationGroupCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.DeleteMealReservationGroupCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.RemoveMembersFromMealReservationGroupCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.UpdateMealReservationGroupDetailsCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.*;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.multilinguals.enterprise.cmrs.query.mrgroup.constant.GroupRoles;

import java.util.*;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
public class MealReservationGroup {
    @AggregateIdentifier
    private MealReservationGroupId id;

    private String name;

    private String description;

    private UserId ownerId;

    private UserId creatorId;

    @AggregateMember
    private Map<GroupMemberId, GroupMember> members = new HashMap<>();

    public MealReservationGroup() {
    }

    @CommandHandler
    public MealReservationGroup(CreateMealReservationGroupCommand command) {
        // 产生新的ID
        MealReservationGroupId mrGroupId = new MealReservationGroupId();

        // 创建人自动成为团队长
        UserId ownerId = command.getCreatorId();

        UserId creatorId = command.getCreatorId();

        Map<GroupMemberId, GroupMember> members = new HashMap<>();

        List<String> memberRoles = Arrays.asList(GroupRoles.GROUP_OWNER, GroupRoles.GROUP_MEMBER, GroupRoles.GROUP_ORDER_TAKER);
        members.put(new GroupMemberId(), new GroupMember(new GroupMemberId(), ownerId, memberRoles));

        apply(new MealReservationGroupCreatedEvent(mrGroupId, command.getName(), command.getDescription(), creatorId, ownerId, members));
    }

    @CommandHandler
    public void handle(UpdateMealReservationGroupDetailsCommand command) {
        apply(new MealReservationGroupDetailsUpdatedEvent(command.getId(), command.getName(), command.getDescription()));
    }

    @CommandHandler
    public void handle(DeleteMealReservationGroupCommand command) throws BizException {
        if (command.getOperatorId().equals(this.ownerId)) {
            apply(new MealReservationGroupDeletedEvent(command.getGroupId()));
        } else {
            throw new BizException(BizErrorCode.USER_NOT_MR_GROUP_OWNER);
        }
    }

    @CommandHandler
    public void handle(RemoveMembersFromMealReservationGroupCommand command) throws BizException {
        if (command.getOperatorId().equals(this.ownerId)) {
            List<GroupMemberId> removedMemberList = new ArrayList<>();
            for (GroupMemberId memberId : command.getMemberIdList()) {
                if (this.members.containsKey(memberId)) {
                    removedMemberList.add(memberId);
                }
            }
            apply(new MembersRemovedFromMealReservationGroupEvent(command.getGroupId(), removedMemberList));
        } else {
            throw new BizException(BizErrorCode.USER_NOT_MR_GROUP_OWNER);
        }
    }

    public void addMembers(List<UserId> userIdList) {
        Map<GroupMemberId, GroupMember> newMembers = new HashMap<>();
        for (UserId userId : userIdList) {
            if (!hasUserInMemberList(userId)) {
                newMembers.put(new GroupMemberId(), new GroupMember(new GroupMemberId(), userId, Collections.singletonList(GroupRoles.GROUP_MEMBER)));
            }
        }

        apply(new MembersAddedToMealReservationGroupEvent(this.id, newMembers));
    }

    public void turnOverOwnerTo(UserId userId) {
        apply(new MealReservationGroupOwnerTurnOverEvent(this.id, this.ownerId, userId));
    }

    @EventSourcingHandler
    public void on(MealReservationGroupCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.ownerId = event.getOwnerId();
        this.creatorId = event.getCreatorId();
        this.members = event.getMembers();
    }

    @EventSourcingHandler
    public void on(MealReservationGroupDetailsUpdatedEvent event) {
        if (event.getName() != null) {
            this.name = event.getName();
        }

        if (event.getDescription() != null) {
            this.description = event.getDescription();
        }
    }

    @EventSourcingHandler
    public void on(MealReservationGroupDeletedEvent event) {
        markDeleted();
    }

    @EventSourcingHandler
    public void on(MealReservationGroupOwnerTurnOverEvent event) {
        this.ownerId = event.getCurrentOwnerId();
    }

    @EventSourcingHandler
    public void on(MembersAddedToMealReservationGroupEvent event) {
        this.members.putAll(event.getNewMembers());
    }

    @EventSourcingHandler
    public void on(MembersRemovedFromMealReservationGroupEvent event) {
        for (GroupMemberId removedMemberId : event.getRemovedMemberIdList()) {
            this.members.remove(removedMemberId);
        }
    }

    private boolean hasUserInMemberList(UserId userId) {
        for (Map.Entry<GroupMemberId, GroupMember> entry : this.members.entrySet()) {
            if (entry.getValue().getUserId().equals(userId)) {
                return true;
            }
        }

        return false;
    }

    public Boolean isOwner(UserId userId) {
        return this.ownerId.equals(userId);
    }

    public MealReservationGroupId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserId getOwnerId() {
        return ownerId;
    }

    public UserId getCreatorId() {
        return creatorId;
    }

    public Map<GroupMemberId, GroupMember> getMembers() {
        return members;
    }
}
