package org.multilinguals.enterprise.cmrs.command.handler.mrgroup;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroup;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.User;
import org.multilinguals.enterprise.cmrs.command.handler.mrgroup.command.TurnOverGroupOwnerCommand;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MRGroupCommandHandler {
    @Resource
    private Repository<User> userAggregateRepository;

    @Resource
    Repository<MealReservationGroup> mrGroupAggregateRepository;

    @CommandHandler
    public void handle(TurnOverGroupOwnerCommand command) throws BizException {
        Aggregate<MealReservationGroup> mealReservationGroupAggregate;

        try {
            mealReservationGroupAggregate = this.mrGroupAggregateRepository.load(command.getGroupId().getIdentifier());
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.MR_GROUP_NOT_EXISTED);
        }

        if (!mealReservationGroupAggregate.invoke(mrGroup -> mrGroup.isOwner(command.getOperatorId()))) {
            throw new BizException(BizErrorCode.USER_NOT_MR_GROUP_OWNER);
        }

        Aggregate<User> userAggregate = this.userAggregateRepository.load(command.getOperatorId().getIdentifier());
    }
}
