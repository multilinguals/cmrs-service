package org.multilinguals.enterprise.cmrs.command.aggregate.mractivity;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityUpdatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.constant.aggregate.mrgroup.MealReservationActivityStatus;

import java.util.Date;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

public class MealReservationActivity {
    @AggregateIdentifier
    private MealReservationActivityId id;

    private MealReservationGroupId groupId;

    private List<RestaurantId> restaurantIdList;

    private MealReservationActivityStatus status;

    private Date startedAt;

    private Date endAt;

    protected MealReservationActivity() {
    }

    public MealReservationActivity(MealReservationGroupId groupId, List<RestaurantId> restaurantIdList, Date startedAt, Date endAt) {
        MealReservationActivityStatus status = MealReservationActivityStatus.PENDING;
        if (startedAt.before(new Date())) {
            status = MealReservationActivityStatus.ONGOING;
        }
        apply(new MealReservationActivityCreatedEvent(
                new MealReservationActivityId(),
                groupId,
                restaurantIdList,
                status,
                startedAt,
                endAt
        ));
    }

    public void update(List<RestaurantId> restaurantIdList, Date startedAt, Date endAt) {
        if (isEditable()) {
            apply(new MealReservationActivityUpdatedEvent(this.id, restaurantIdList, startedAt, endAt));
        }
    }

    @EventSourcingHandler
    public void on(MealReservationActivityCreatedEvent event) {
        this.id = event.getId();
        this.groupId = event.getGroupId();
        this.restaurantIdList = event.getRestaurantIdList();
        this.status = MealReservationActivityStatus.ONGOING;
        this.startedAt = event.getStartedAt();
        this.endAt = event.getEndAt();
    }

    @EventSourcingHandler
    public void on(MealReservationActivityUpdatedEvent event) {
        this.restaurantIdList = event.getRestaurantIdList();
        this.startedAt = event.getStartedAt();
        this.endAt = event.getEndAt();
    }

    public boolean isEditable() {
        return this.status.equals(MealReservationActivityStatus.PENDING);
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

    public Date getStartedAt() {
        return startedAt;
    }

    public Date getEndAt() {
        return endAt;
    }
}
