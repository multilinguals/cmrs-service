package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.CreateMealReservationGroupCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.DeleteMealReservationGroupCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupDeletedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.UserNotMRGroupOwnerException;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
public class MealReservationGroup {
    private MealReservationGroupId id;

    private String name;

    private UserId ownerId;

    private UserId creatorId;

    private List<UserId> orderTakerIdList;

    private List<UserId> memberIdList;

    public MealReservationGroup() {
    }

    @CommandHandler
    public MealReservationGroup(CreateMealReservationGroupCommand command) {
        // 产生新的ID
        MealReservationGroupId mrGroupId = new MealReservationGroupId();

        // 创建人自动成为团队长
        UserId ownerId = command.getCreatorId();

        UserId creatorId = command.getCreatorId();

        // 创建人默认成为团队的点餐员
        List<UserId> orderTakerIdList = new ArrayList<>();
        orderTakerIdList.add(command.getCreatorId());

        // 创建人默认成为团队的成员
        List<UserId> memberIdList = new ArrayList<>();
        memberIdList.add(command.getCreatorId());

        apply(new MealReservationCreatedEvent(mrGroupId, command.getName(), creatorId, ownerId, orderTakerIdList, memberIdList));
    }

    @CommandHandler
    public void handle(DeleteMealReservationGroupCommand command) throws UserNotMRGroupOwnerException {
        if (command.getOperatorId().equals(this.ownerId)) {
            apply(new MealReservationGroupDeletedEvent(command.getGroupId()));
        } else {
            throw new UserNotMRGroupOwnerException();
        }
    }

    @EventSourcingHandler
    public void on(MealReservationCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.ownerId = event.getOwnerId();
        this.creatorId = event.getCreatorId();
        this.orderTakerIdList = event.getOrderTakerIdList();
        this.memberIdList = event.getMemberIdList();
    }

    @EventSourcingHandler
    public void on(MealReservationGroupDeletedEvent event) {
        markDeleted();
    }

    public MealReservationGroupId getId() {
        return id;
    }

    public String getName() {
        return name;
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
