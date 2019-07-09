package org.multilinguals.enterprise.cmrs.query.restaurant;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.RestaurantCreatedEvent;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsView;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class RestaurantDetailsViewHandler {
    private final UserDetailsViewRepository userDetailsViewRepository;
    private final RestaurantDetailsViewRepository restaurantDetailsViewRepository;

    @Autowired
    public RestaurantDetailsViewHandler(UserDetailsViewRepository userDetailsViewRepository, RestaurantDetailsViewRepository restaurantDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
        this.restaurantDetailsViewRepository = restaurantDetailsViewRepository;
    }

    @EventHandler
    public void on(RestaurantCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        Optional<UserDetailsView> optionalUserDetailsView = this.userDetailsViewRepository.findById(event.getCreatorId().getIdentifier());
        String realName = null;
        if (optionalUserDetailsView.isPresent()) {
            realName = optionalUserDetailsView.get().getRealName();
        }
        RestaurantDetailsView restaurantDetailsView = new RestaurantDetailsView(
                event.getId().getIdentifier(),
                event.getName(),
                event.getDescription(),
                event.getCreatorId().getIdentifier(),
                realName,
                new Date(createdTime.toEpochMilli())
        );

        this.restaurantDetailsViewRepository.save(restaurantDetailsView);
    }
}
