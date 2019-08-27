package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.query.mrgroup.MealReservationGroupDetailsRepository;
import org.multilinguals.enterprise.cmrs.query.mrgroup.MealReservationGroupDetailsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealReservationGroupQueryController {
    private MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository;

    @Autowired
    public MealReservationGroupQueryController(MealReservationGroupDetailsRepository mealReservationGroupDetailsRepository) {
        this.mealReservationGroupDetailsRepository = mealReservationGroupDetailsRepository;
    }

    @GetMapping("/get-details-of-mr-group/{id}")
    @PreAuthorize("isAuthenticated()")
    public MealReservationGroupDetailsView queryAllDishType(@PathVariable String id) {
        return this.mealReservationGroupDetailsRepository.findById(id).orElseThrow(null);
    }
}
