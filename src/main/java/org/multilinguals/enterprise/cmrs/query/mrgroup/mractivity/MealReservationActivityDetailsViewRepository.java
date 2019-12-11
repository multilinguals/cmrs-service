package org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MealReservationActivityDetailsViewRepository extends MongoRepository<MealReservationActivityDetailsView, String> {
    MealReservationActivityDetailsView findFirstByGroupIdAndStatusIsNotIn(String groupId, List<Integer> status);
}
