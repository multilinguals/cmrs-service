package org.multilinguals.enterprise.cmrs.command.handler.mractivity;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivity;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command.CreateMealReservationActivityCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroup;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.Restaurant;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MealReservationActivityCommandHandler {
    @Resource
    private Repository<MealReservationActivity> mrActivityAggregateRepository;

    @Resource
    Repository<MealReservationGroup> mrGroupAggregateRepository;

    @Resource
    Repository<Restaurant> restaurantAggregateRepository;

    @CommandHandler
    public MealReservationActivityId handle(CreateMealReservationActivityCommand command) throws Exception {
        Aggregate<MealReservationGroup> mealReservationGroupAggregate = this.mrGroupAggregateRepository.load(command.getGroupId().getIdentifier());
        if (!mealReservationGroupAggregate.invoke(mrGroup -> mrGroup.isOrderTaker(command.getOperatorId()))) {
            throw new BizException(BizErrorCode.USER_NOT_ORDER_TAKER);
        }

        Aggregate<MealReservationActivity> mealReservationActivityAggregate = mrActivityAggregateRepository.newInstance(() ->
                new MealReservationActivity(command.getGroupId(), command.getRestaurantIdList(), command.getStartedAt(), command.getEndAt())
        );

        for (RestaurantId id : command.getRestaurantIdList()) {
            try {
                Aggregate<Restaurant> restaurantAggregate = this.restaurantAggregateRepository.load(id.getIdentifier());
                if (restaurantAggregate.isDeleted()) {
                    throw new BizException(BizErrorCode.RESTAURANT_NOT_EXISTED);
                }
            } catch (AggregateNotFoundException ex) {
                throw new BizException(BizErrorCode.RESTAURANT_NOT_EXISTED);
            }
        }

        return mealReservationActivityAggregate.invoke(MealReservationActivity::getId);
    }
}
