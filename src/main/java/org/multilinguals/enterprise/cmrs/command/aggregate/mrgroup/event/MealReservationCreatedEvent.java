package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.AbstractEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.List;

public class MealReservationCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private MealReservationGroupId id;

    private String name;

    private UserId ownerId;

    private UserId creatorId;

    private List<UserId> orderTakerIdList;

    private List<UserId> memberIdList;

    public MealReservationCreatedEvent(MealReservationGroupId id, String name, UserId ownerId, UserId creatorId, List<UserId> orderTakerIdList, List<UserId> memberIdList) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.creatorId = creatorId;
        this.orderTakerIdList = orderTakerIdList;
        this.memberIdList = memberIdList;
    }

    public MealReservationGroupId getId() {
        return id;
    }

    public void setId(MealReservationGroupId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserId getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UserId ownerId) {
        this.ownerId = ownerId;
    }

    public UserId getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UserId creatorId) {
        this.creatorId = creatorId;
    }

    public List<UserId> getOrderTakerIdList() {
        return orderTakerIdList;
    }

    public void setOrderTakerIdList(List<UserId> orderTakerIdList) {
        this.orderTakerIdList = orderTakerIdList;
    }

    public List<UserId> getMemberIdList() {
        return memberIdList;
    }

    public void setMemberIdList(List<UserId> memberIdList) {
        this.memberIdList = memberIdList;
    }
}
