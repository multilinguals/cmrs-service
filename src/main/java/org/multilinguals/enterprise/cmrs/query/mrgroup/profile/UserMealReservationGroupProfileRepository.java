package org.multilinguals.enterprise.cmrs.query.mrgroup.profile;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMealReservationGroupProfileRepository extends MongoRepository<UserMealReservationGroupProfileView, String> {
}
