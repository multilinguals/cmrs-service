package org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.CreateMealReservationCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

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
    public MealReservationGroup(CreateMealReservationCommand command) {
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

    @EventSourcingHandler
    public void on(MealReservationCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.ownerId = event.getOwnerId();
        this.creatorId = event.getCreatorId();
        this.orderTakerIdList = event.getOrderTakerIdList();
        this.memberIdList = event.getMemberIdList();
    }
}
