package org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityClosedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.event.MealReservationActivityUpdatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.constant.aggregate.mrgroup.MealReservationActivityStatus;
import org.multilinguals.enterprise.cmrs.query.restaurant.RestaurantDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MealReservationActivityViewHandler {
    private MealReservationActivityDetailsViewRepository mealReservationActivityDetailsViewRepository;

    private RestaurantDetailsViewRepository restaurantDetailsViewRepository;

    @Autowired
    public MealReservationActivityViewHandler(MealReservationActivityDetailsViewRepository mealReservationActivityDetailsViewRepository, RestaurantDetailsViewRepository restaurantDetailsViewRepository) {
        this.mealReservationActivityDetailsViewRepository = mealReservationActivityDetailsViewRepository;
        this.restaurantDetailsViewRepository = restaurantDetailsViewRepository;
    }

    @EventHandler
    public void on(MealReservationActivityCreatedEvent event, @Timestamp java.time.Instant eventCreatedTime) {
        List<ActivityRestaurantProfileView> activityRestaurantProfileViews = new ArrayList<>();
        for (RestaurantId restaurantId : event.getRestaurantIdList()) {
            this.restaurantDetailsViewRepository.findById(restaurantId.getIdentifier()).ifPresent(restaurantDetailsView -> {
                activityRestaurantProfileViews.add(new ActivityRestaurantProfileView(restaurantDetailsView.getId(), restaurantDetailsView.getName()));
            });
        }

        MealReservationActivityDetailsView mealReservationActivityDetailsView = new MealReservationActivityDetailsView(
                event.getId().getIdentifier(),
                event.getGroupId().getIdentifier(),
                activityRestaurantProfileViews, event.getStatus().getValue(),
                new Date(eventCreatedTime.toEpochMilli())
        );

        this.mealReservationActivityDetailsViewRepository.save(mealReservationActivityDetailsView);
    }

    @EventHandler
    public void on(MealReservationActivityUpdatedEvent event, @Timestamp java.time.Instant eventCreatedTime) {
        this.mealReservationActivityDetailsViewRepository.findById(event.getId().getIdentifier()).ifPresent(activity -> {
            List<ActivityRestaurantProfileView> activityRestaurantProfileViews = new ArrayList<>();

            for (RestaurantId id : event.getRestaurantIdList()) {
                this.restaurantDetailsViewRepository.findById(id.getIdentifier()).ifPresent(restaurantDetailsView -> {
                    activityRestaurantProfileViews.add(new ActivityRestaurantProfileView(restaurantDetailsView.getId(), restaurantDetailsView.getName()));
                });
            }

            activity.setRestaurantProfileViews(activityRestaurantProfileViews);
            activity.setUpdatedAt(new Date(eventCreatedTime.toEpochMilli()));

            this.mealReservationActivityDetailsViewRepository.save(activity);
        });
    }

    @EventHandler
    public void on(MealReservationActivityClosedEvent event, @Timestamp java.time.Instant eventCreatedTime) {
        this.mealReservationActivityDetailsViewRepository.findById(event.getId().getIdentifier()).ifPresent(activity -> {
            activity.setStatus(MealReservationActivityStatus.CLOSED.getValue());

            activity.setUpdatedAt(new Date(eventCreatedTime.toEpochMilli()));
            this.mealReservationActivityDetailsViewRepository.save(activity);
        });
    }
}
