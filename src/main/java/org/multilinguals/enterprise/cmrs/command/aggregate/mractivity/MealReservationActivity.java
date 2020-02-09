package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command.CloseMealReservationActivityCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityClosedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityStartedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityUpdatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrorder.MealReservationOrder;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrorder.MealReservationOrderId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.constant.aggregate.mrgroup.MealReservationActivityStatus;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

public class MealReservationActivity {
    @AggregateIdentifier
    private MealReservationActivityId id;

    private MealReservationGroupId groupId;

    private List<RestaurantId> restaurantIdList;

    private MealReservationActivityStatus status;

    private MealReservationOrderId orderId;

    protected MealReservationActivity() {
    }

    public MealReservationActivity(MealReservationGroupId groupId, List<RestaurantId> restaurantIdList) throws Exception {
        MealReservationActivityId newId = new MealReservationActivityId();
        Aggregate<MealReservationOrder> mrOrderAggregate = AggregateLifecycle.createNew(MealReservationOrder.class, () -> new MealReservationOrder(newId));

        apply(new MealReservationActivityCreatedEvent(
                newId,
                groupId,
                restaurantIdList,
                MealReservationActivityStatus.ONGOING,
                mrOrderAggregate.invoke(MealReservationOrder::getId)
        ));
    }

    @CommandHandler
    public void handle(CloseMealReservationActivityCommand command) {
        apply(new MealReservationActivityClosedEvent(command.getId()));
    }

    public void update(List<RestaurantId> restaurantIdList) {
        apply(new MealReservationActivityUpdatedEvent(this.id, restaurantIdList));
    }

    @EventSourcingHandler
    public void on(MealReservationActivityCreatedEvent event) {
        this.id = event.getId();
        this.groupId = event.getGroupId();
        this.restaurantIdList = event.getRestaurantIdList();
        this.status = MealReservationActivityStatus.ONGOING;
        this.orderId = event.getOrderId();
    }

    @EventSourcingHandler
    public void on(MealReservationActivityStartedEvent event) {
        this.status = MealReservationActivityStatus.ONGOING;
    }

    @EventSourcingHandler
    public void on(MealReservationActivityUpdatedEvent event) {
        this.restaurantIdList = event.getRestaurantIdList();
    }

    @EventSourcingHandler
    public void on(MealReservationActivityClosedEvent event) {
        this.status = MealReservationActivityStatus.CLOSED;
    }

    public boolean isEditable() {
        return this.status.equals(MealReservationActivityStatus.ONGOING);
    }

    public MealReservationActivityId getId() {
        return id;
    }

    public MealReservationGroupId getGroupId() {
        return groupId;
    }

    public List<RestaurantId> getRestaurantIdList() {
        return restaurantIdList;
    }

    public MealReservationActivityStatus getStatus() {
        return status;
    }

    public MealReservationOrderId getOrderId() {
        return orderId;
    }
}
