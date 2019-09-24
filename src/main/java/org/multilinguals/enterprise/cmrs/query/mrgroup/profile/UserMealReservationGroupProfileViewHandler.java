package org.multilinguals.enterprise.cmrs.query.mrgroup.profile;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupDetailsUpdatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.event.MealReservationGroupOwnerTurnOverEvent;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class UserMealReservationGroupProfileViewHandler {
    private UserDetailsViewRepository userDetailsViewRepository;
    private UserMealReservationGroupProfileRepository userMealReservationGroupProfileRepository;

    @Autowired
    public UserMealReservationGroupProfileViewHandler(UserDetailsViewRepository userDetailsViewRepository, UserMealReservationGroupProfileRepository userMealReservationGroupProfileRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
        this.userMealReservationGroupProfileRepository = userMealReservationGroupProfileRepository;
    }

    @EventHandler
    public void on(MealReservationGroupCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        this.userDetailsViewRepository.findById(event.getCreatorId().getIdentifier()).ifPresent(creator -> {
            UserMealReservationGroupProfileView userMealReservationGroupProfileView = new UserMealReservationGroupProfileView(
                    creator.getId(),
                    event.getId().getIdentifier(),
                    event.getName(),
                    creator.getId(),
                    creator.getRealName(),
                    new Date(createdTime.toEpochMilli())
            );

            this.userMealReservationGroupProfileRepository.save(userMealReservationGroupProfileView);
        });
    }

    @EventHandler
    public void on(MealReservationGroupDetailsUpdatedEvent event, @Timestamp java.time.Instant createdTime) {
        List<UserMealReservationGroupProfileView> userMealReservationGroupProfileViewList = this.userMealReservationGroupProfileRepository.findAll(
                Example.of(new UserMealReservationGroupProfileView(event.getMealReservationGroupId().getIdentifier()))
        );

        for (UserMealReservationGroupProfileView groupProfile : userMealReservationGroupProfileViewList) {
            if (StringUtils.isNotEmpty(event.getName())) {
                groupProfile.setName(event.getName());
                groupProfile.setUpdatedAt(new Date(createdTime.toEpochMilli()));
            }
        }

        this.userMealReservationGroupProfileRepository.saveAll(userMealReservationGroupProfileViewList);
    }

    @EventHandler
    public void on(MealReservationGroupOwnerTurnOverEvent event, @Timestamp java.time.Instant createdTime) {
        List<UserMealReservationGroupProfileView> userMealReservationGroupProfileViewList = this.userMealReservationGroupProfileRepository.findAll(
                Example.of(new UserMealReservationGroupProfileView(event.getMealReservationGroupId().getIdentifier()))
        );

        this.userDetailsViewRepository.findById(event.getCurrentOwnerId().getIdentifier()).ifPresent(userDetailsView -> {
            String realName = userDetailsView.getRealName();
            for (UserMealReservationGroupProfileView groupProfile : userMealReservationGroupProfileViewList) {
                groupProfile.setOwnerId(event.getCurrentOwnerId().getIdentifier());
                groupProfile.setOwnerId(realName);
                groupProfile.setUpdatedAt(new Date(createdTime.toEpochMilli()));
            }

            this.userMealReservationGroupProfileRepository.saveAll(userMealReservationGroupProfileViewList);
        });
    }
}
