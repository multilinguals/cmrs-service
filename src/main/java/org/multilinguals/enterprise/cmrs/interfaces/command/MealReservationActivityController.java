package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.MealReservationActivityId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command.CreateMealReservationActivityCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mractivity.command.UpdateMealReservationActivityCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup.CreateMealReservationActivityDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup.UpdateMealReservationActivityDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.AggregateCreatedDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

@RestController
public class MealReservationActivityController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/create-mr-group/{groupId}/-mr-activity")
    @PreAuthorize("isAuthenticated()")
    public AggregateCreatedDTO<String> createActivity(@PathVariable String groupId, @RequestBody @Validated CreateMealReservationActivityDTO dto, @RequestAttribute String reqSenderId) throws ParseException {
        MealReservationActivityId activityId = this.commandGateway.sendAndWait(new CreateMealReservationActivityCommand(
                new MealReservationGroupId(groupId),
                dto.getRestaurantIdList(),
                new UserId(reqSenderId),
                dto.getStartedAt(),
                dto.getEndAt())
        );
        return new AggregateCreatedDTO<>(activityId.getIdentifier());
    }

    @PostMapping("/update-mr-activity/{activityId}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActivity(@PathVariable String activityId, @Validated @RequestBody UpdateMealReservationActivityDTO dto, @RequestAttribute String reqSenderId) throws ParseException {
        this.commandGateway.sendAndWait(new UpdateMealReservationActivityCommand(
                new MealReservationActivityId(activityId),
                dto.getRestaurantIdList(),
                new UserId(reqSenderId)
        ));
    }
}
