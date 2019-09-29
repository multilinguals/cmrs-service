package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.GroupMemberId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.MealReservationGroupId;
import org.multilinguals.enterprise.cmrs.command.aggregate.mrgroup.command.*;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.command.handler.mrgroup.command.TurnOverGroupOwnerCommand;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup.AddMembersToGroupDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup.CreateMealReservationGroupDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup.RemoveMembersToGroupDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup.UpdateMealReservationGroupDetailsDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.AggregateCreatedDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MealReservationGroupController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/create-mr-group")
    @PreAuthorize("hasAnyRole('ROLE_MR_GROUP_ADMIN')")
    public AggregateCreatedDTO<String> createMRGroup(@RequestBody CreateMealReservationGroupDTO dto, @RequestAttribute String reqSenderId) {
        MealReservationGroupId mrGroupId = this.commandGateway.sendAndWait(
                new CreateMealReservationGroupCommand(
                        dto.getName(), dto.getDescription(), new UserId(reqSenderId)
                )
        );
        return new AggregateCreatedDTO<>(mrGroupId.getIdentifier());
    }

    @PostMapping("/update-details-of-mr-group/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MR_GROUP_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMRGroupDetails(@PathVariable String id, @RequestBody @Validated UpdateMealReservationGroupDetailsDTO dto) throws BizException {
        try {
            this.commandGateway.sendAndWait(new UpdateMealReservationGroupDetailsCommand(
                    new MealReservationGroupId(id), dto.getName(), dto.getDescription())
            );
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.MR_GROUP_NOT_EXISTED);
        }
    }

    @PostMapping("/delete-mr-group/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MR_GROUP_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMRGroup(@PathVariable String id, @RequestAttribute String reqSenderId) {
        this.commandGateway.sendAndWait(new DeleteMealReservationGroupCommand(new MealReservationGroupId(id), new UserId(reqSenderId)));
    }

    @PostMapping("/add-members-to-mr-group/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MR_GROUP_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addMembersToMRGroup(@PathVariable String id, @RequestBody @Validated AddMembersToGroupDTO dto, @RequestAttribute String reqSenderId) throws BizException {
        try {
            List<UserId> memberIdList = new ArrayList<>();
            for (String memberId : dto.getUserIdList()) {
                memberIdList.add(new UserId(memberId));
            }

            commandGateway.sendAndWait(new AddMembersToMealReservationGroupCommand(
                    new MealReservationGroupId(id), memberIdList, new UserId(reqSenderId))
            );
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.USER_NOT_EXISTED);
        }
    }

    @PostMapping("/remove-members-from-mr-group/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MR_GROUP_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMembersToMRGroup(@PathVariable String id, @RequestBody @Validated RemoveMembersToGroupDTO dto, @RequestAttribute String reqSenderId) throws BizException {
        try {
            List<GroupMemberId> memberIdList = new ArrayList<>();
            for (String memberId : dto.getMemberIdList()) {
                memberIdList.add(new GroupMemberId(memberId));
            }

            commandGateway.sendAndWait(new RemoveMembersFromMealReservationGroupCommand(
                    new MealReservationGroupId(id), memberIdList, new UserId(reqSenderId))
            );
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.MR_GROUP_NOT_EXISTED);
        }
    }

    @PostMapping("/set-roles-to-member/{memberId}/of-group/{groupId}")
    @PreAuthorize("hasAnyRole('ROLE_MR_GROUP_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setRolesToMember(@PathVariable String memberId, @PathVariable String groupId, @RequestAttribute String reqSenderId) {
        // this.commandGateway.sendAndWait(new TurnOverGroupOwnerCommand(new UserId(reqSenderId), new MealReservationGroupId(groupId), new UserId(memberId)));
    }

    @PostMapping("/turn-over-owner-of-group/{groupId}/to-user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_MR_GROUP_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void turnOverOwner(@PathVariable String groupId, @PathVariable String userId, @RequestAttribute String reqSenderId) {
        this.commandGateway.sendAndWait(new TurnOverGroupOwnerCommand(new UserId(reqSenderId), new MealReservationGroupId(groupId), new UserId(userId)));
    }
}
