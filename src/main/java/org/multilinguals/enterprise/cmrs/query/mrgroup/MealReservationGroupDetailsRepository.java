package org.multilinguals.enterprise.cmrs.query.mrgroup;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealReservationGroupDetailsRepository extends MongoRepository<MealReservationGroupDetailsView, String> {
}
