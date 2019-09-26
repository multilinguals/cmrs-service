package org.multilinguals.enterprise.cmrs.command.handler.mrgroup;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroup;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.AddMembersToMealReservationGroupCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.Role;
import org.multilinguals.enterprise.cmrs.command.aggregate.role.RoleId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.User;
import org.multilinguals.enterprise.cmrs.command.handler.mrgroup.command.TurnOverGroupOwnerCommand;
import org.multilinguals.enterprise.cmrs.constant.aggregate.role.DefaultRoleName;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MRGroupCommandHandler {
    @Resource
    private Repository<User> userAggregateRepository;

    @Resource
    private Repository<Role> roleAggregateRepository;

    @Resource
    Repository<MealReservationGroup> mrGroupAggregateRepository;

    @CommandHandler
    public void handle(AddMembersToMealReservationGroupCommand command) throws BizException {
        Aggregate<MealReservationGroup> mealReservationGroupAggregate;

        try {
            mealReservationGroupAggregate = this.mrGroupAggregateRepository.load(command.getGroupId().getIdentifier());
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.MR_GROUP_NOT_EXISTED);
        }

        if (!mealReservationGroupAggregate.invoke(mrGroup -> mrGroup.isOwner(command.getOperatorId()))) {
            throw new BizException(BizErrorCode.USER_NOT_MR_GROUP_OWNER);
        }

//        try {
//            Aggregate<User> userAggregate = this.userAggregateRepository.load(command.getTargetUserId().getIdentifier());
//            if (isOrderTaker(userAggregate)) {
//                mealReservationGroupAggregate.execute(mrGroup -> mrGroup.turnOverOwnerTo(command.getTargetUserId()));
//            } else {
//                throw new BizException(BizErrorCode.USER_NOT_ORDER_TAKER);
//            }
//        } catch (AggregateNotFoundException ex) {
//            throw new BizException(BizErrorCode.USER_NOT_EXISTED);
//        }
    }

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

        try {
            Aggregate<User> userAggregate = this.userAggregateRepository.load(command.getTargetUserId().getIdentifier());
            if (isOrderTaker(userAggregate)) {
                mealReservationGroupAggregate.execute(mrGroup -> mrGroup.turnOverOwnerTo(command.getTargetUserId()));
            } else {
                throw new BizException(BizErrorCode.USER_NOT_ORDER_TAKER);
            }
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTED);
        }
    }

    private boolean isOrderTaker(Aggregate<User> userAggregate) {
        List<RoleId> roleIdList = userAggregate.invoke(User::getRoleIdList);

        boolean isOrderTaker = false;
        for (RoleId roleId : roleIdList) {
            Aggregate<Role> roleAggregate = this.roleAggregateRepository.load(roleId.getIdentifier());
            String roleName = roleAggregate.invoke(Role::getName);
            if (DefaultRoleName.ORDER_TAKER.equals(roleName)) {
                isOrderTaker = true;
                break;
            }
        }

        return isOrderTaker;
    }
}
