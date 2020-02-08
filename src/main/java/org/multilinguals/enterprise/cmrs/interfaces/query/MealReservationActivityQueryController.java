package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.constant.aggregate.mrgroup.MealReservationActivityStatus;
import org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity.MealReservationActivityDetailsView;
import org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity.MealReservationActivityDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealReservationActivityQueryController {
    private MealReservationActivityDetailsViewRepository mealReservationActivityDetailsViewRepository;

    @Autowired
    public MealReservationActivityQueryController(MealReservationActivityDetailsViewRepository mealReservationActivityDetailsViewRepository) {
        this.mealReservationActivityDetailsViewRepository = mealReservationActivityDetailsViewRepository;
    }

    @GetMapping("/get-details-of-mr-activity/{id}")
    @PreAuthorize("isAuthenticated()")
    public MealReservationActivityDetailsView queryMRActivityDetails(@PathVariable String id, @RequestAttribute String reqSenderId) {
        return this.mealReservationActivityDetailsViewRepository.findById(id)
                .orElseGet(() -> null);
    }

    @GetMapping("/get-active-profile-of-mr-activity-of-mr-group/{id}")
    @PreAuthorize("isAuthenticated()")
    public MealReservationActivityDetailsView queryActiveProfileOfActivity(@PathVariable String id, @RequestAttribute String reqSenderId) {
        return this.mealReservationActivityDetailsViewRepository.findFirstByGroupIdAndStatusIsNot(
                id,
                MealReservationActivityStatus.CLOSED.getValue()
        );
    }
}
