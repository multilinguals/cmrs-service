package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.query.mrgroup.details.MealReservationGroupDetailsRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.details.MealReservationGroupDetailsView;
import org.multilinguals.enterprise.cmrs.query.mrgroup.member.GroupMemberView;
import org.multilinguals.enterprise.cmrs.query.mrgroup.member.GroupMemberViewRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.profile.UserMealReservationGroupProfileRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.profile.UserMealReservationGroupProfileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<GroupMemberView> queryMembersOfMRGroup(@PathVariable String id, @RequestParam String roleName, @RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        return this.groupMemberViewRepository.findAll(Example.of(new GroupMemberView(roleName)), sort);
    }
}
