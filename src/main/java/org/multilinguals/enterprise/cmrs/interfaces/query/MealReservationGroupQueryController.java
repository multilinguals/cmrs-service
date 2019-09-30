package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.apache.commons.lang3.StringUtils;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.CMRSPage;
import org.multilinguals.enterprise.cmrs.query.mrgroup.details.MealReservationGroupDetailsRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.details.MealReservationGroupDetailsView;
import org.multilinguals.enterprise.cmrs.query.mrgroup.member.GroupMemberView;
import org.multilinguals.enterprise.cmrs.query.mrgroup.member.GroupMemberViewRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.profile.UserMealReservationGroupProfileRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.profile.UserMealReservationGroupProfileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class MealReservationGroupQueryController {
    private MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository;

    private UserMealReservationGroupProfileRepository userMealReservationGroupProfileRepository;

    private GroupMemberViewRepository groupMemberViewRepository;

    @Autowired
    public MealReservationGroupQueryController(MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository, UserMealReservationGroupProfileRepository userMealReservationGroupProfileRepository, GroupMemberViewRepository groupMemberViewRepository) {
        this.mealReservationGroupDetailsRepository = mealReservationGroupDetailsRepository;
        this.userMealReservationGroupProfileRepository = userMealReservationGroupProfileRepository;
        this.groupMemberViewRepository = groupMemberViewRepository;
    }

    @GetMapping("/get-profile-of-self-mr-group")
    @PreAuthorize("isAuthenticated()")
    public UserMealReservationGroupProfileView queryMRGroupProfile(@RequestAttribute String reqSenderId) {
        return this.userMealReservationGroupProfileRepository.findById(reqSenderId).orElseGet(() -> null);
    }

    @GetMapping("/get-details-of-mr-group/{id}")
    @PreAuthorize("isAuthenticated()")
    public MealReservationGroupDetailsView queryMRGroupDetails(@PathVariable String id) {
        return this.mealReservationGroupDetailsRepository.findById(id).orElseGet(() -> null);
    }

    @GetMapping("/get-members-of-mr-group/{id}")
    @PreAuthorize("isAuthenticated()")
    public CMRSPage<GroupMemberView> queryMembersOfMRGroup(@PathVariable String id, @RequestParam(required = false) String roleName, @RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");

        Page<GroupMemberView> groupMemberViews = null;

        if (StringUtils.isNotEmpty(roleName) && StringUtils.isNotBlank(roleName)) {
            groupMemberViews = this.groupMemberViewRepository.findAllByMrGroupIdAndGroupRoles(
                    id,
                    roleName,
                    PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sort)
            );
        } else {
            groupMemberViews = this.groupMemberViewRepository.findAllByMrGroupId(id, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sort));
        }

        return new CMRSPage<>(groupMemberViews);
    }
}
