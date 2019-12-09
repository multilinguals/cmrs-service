package org.multilinguals.enterprise.cmrs.command.handler.mractivity;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivity;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command.CreateMealReservationActivityCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command.UpdateMealReservationActivityCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroup;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.Restaurant;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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

        restaurantsMustBeExisted(command.getRestaurantIdList());

        return mealReservationActivityAggregate.invoke(MealReservationActivity::getId);
    }

    @CommandHandler
    public void handle(UpdateMealReservationActivityCommand command) throws BizException {
        Aggregate<MealReservationActivity> mrActivityAggregate = this.mrActivityAggregateRepository.load(command.getId().getIdentifier());
        MealReservationGroupId groupId = mrActivityAggregate.invoke(MealReservationActivity::getGroupId);

        Aggregate<MealReservationGroup> mealReservationGroupAggregate = this.mrGroupAggregateRepository.load(groupId.getIdentifier());
        if (!mealReservationGroupAggregate.invoke(mrGroup -> mrGroup.isOrderTaker(command.getOperatorId()))) {
            throw new BizException(BizErrorCode.USER_NOT_ORDER_TAKER);
        }

        restaurantsMustBeExisted(command.getRestaurantIdList());

        if (!mrActivityAggregate.invoke(MealReservationActivity::isEditable)) {
            throw new BizException(BizErrorCode.ACTIVITY_STATUS_NOT_EDITABLE);
        }

        mrActivityAggregate.execute(activity -> {
                    activity.update(command.getRestaurantIdList(), command.getStartedAt(), command.getEndAt());
                }
        );
    }

    private void restaurantsMustBeExisted(List<RestaurantId> restaurantIdList) throws BizException {
        for (RestaurantId id : restaurantIdList) {
            try {
                Aggregate<Restaurant> restaurantAggregate = this.restaurantAggregateRepository.load(id.getIdentifier());

                if (restaurantAggregate.isDeleted()) {
                    throw new BizException(BizErrorCode.RESTAURANT_NOT_EXISTED);
                }
            } catch (AggregateNotFoundException ex) {
                throw new BizException(BizErrorCode.RESTAURANT_NOT_EXISTED);
            }
        }
    }
}
