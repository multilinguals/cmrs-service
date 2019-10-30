package org.multilinguals.enterprise.cmrs.query.restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantDetailsViewRepository extends MongoRepository<RestaurantDetailsView, String> {
    Page<RestaurantDetailsView> findByNameLike(String realName, Pageable pageable);
}
