package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMember;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.Map;

public class MealReservationGroupCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId id;

    private String name;

    private String description;

    private UserId ownerId;

    private UserId creatorId;

    private Map<UserId, GroupMember> members;

    public MealReservationGroupCreatedEvent(MealReservationGroupId id, String name, String description, UserId ownerId, UserId creatorId, Map<UserId, GroupMember> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.creatorId = creatorId;
        this.members = members;
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

    public Map<UserId, GroupMember> getMembers() {
        return members;
    }
}
