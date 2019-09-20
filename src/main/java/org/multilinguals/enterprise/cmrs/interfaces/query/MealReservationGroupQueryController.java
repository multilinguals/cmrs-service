package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.query.mrgroup.details.MealReservationGroupDetailsRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.details.MealReservationGroupDetailsView;
import org.multilinguals.enterprise.cmrs.query.mrgroup.profile.UserMealReservationGroupProfileRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.profile.UserMealReservationGroupProfileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealReservationGroupQueryController {
    private MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository;

    private UserMealReservationGroupProfileRepository userMealReservationGroupProfileRepository;

    @Autowired
    public MealReservationGroupQueryController(MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository, UserMealReservationGroupProfileRepository userMealReservationGroupProfileRepository) {
        this.mealReservationGroupDetailsRepository = mealReservationGroupDetailsRepository;
        this.userMealReservationGroupProfileRepository = userMealReservationGroupProfileRepository;
    }

    @GetMapping("/get-profile-of-self-mr-group")
    @PreAuthorize("isAuthenticated()")
    public UserMealReservationGroupProfileView queryMrGroupProfile(@RequestAttribute String reqSenderId) {
        return this.userMealReservationGroupProfileRepository.findById(reqSenderId).orElseGet(() -> null);
    }

    @GetMapping("/get-details-of-mr-group/{id}")
    @PreAuthorize("isAuthenticated()")
    public MealReservationGroupDetailsView queryAllDishType(@PathVariable String id) {
        return this.mealReservationGroupDetailsRepository.findById(id).orElseGet(() -> null);
    }
}
