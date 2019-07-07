package org.multilinguals.enterprise.cmrs.query.restaurant;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantDetailsViewRepository extends MongoRepository<RestaurantDetailsView, String> {
}
