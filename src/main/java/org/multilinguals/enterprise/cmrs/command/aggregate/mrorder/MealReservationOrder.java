package org.multilinguals.enterprise.cmrs.command.aggregate.mrorder;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrorder.event.MealReservationOrderCreatedEvent;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

public class MealReservationOrder {
    @AggregateIdentifier
    private MealReservationOrderId id;

    private MealReservationActivityId activityId;

    private BigDecimal totalPrice;

    @AggregateMember
    private HashMap<MealReservationOrderItemId, MealReservationOrderItem> items;

    protected MealReservationOrder() {
    }

    public MealReservationOrder(MealReservationActivityId activityId) {
        apply(new MealReservationOrderCreatedEvent(new MealReservationOrderId(), activityId));
    }

    @EventSourcingHandler
    public void on(MealReservationOrderCreatedEvent event) {
        this.id = event.getId();
        this.activityId = event.getActivityId();
        this.totalPrice = BigDecimal.ZERO;
        this.items = new HashMap<>();
    }

    public MealReservationOrderId getId() {
        return id;
    }

    public MealReservationActivityId getActivityId() {
        return activityId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public HashMap<MealReservationOrderItemId, MealReservationOrderItem> getItems() {
        return items;
    }
}
