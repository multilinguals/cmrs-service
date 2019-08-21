package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.List;

public class MealReservationGroupCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId id;

    private String name;

    private String description;

    private UserId ownerId;

    private UserId creatorId;

    private List<UserId> orderTakerIdList;

    private List<UserId> memberIdList;

    public MealReservationGroupCreatedEvent(MealReservationGroupId id, String name, String description, UserId ownerId, UserId creatorId, List<UserId> orderTakerIdList, List<UserId> memberIdList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.creatorId = creatorId;
        this.orderTakerIdList = orderTakerIdList;
        this.memberIdList = memberIdList;
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

    public List<UserId> getOrderTakerIdList() {
        return orderTakerIdList;
    }

    public List<UserId> getMemberIdList() {
        return memberIdList;
    }
}
