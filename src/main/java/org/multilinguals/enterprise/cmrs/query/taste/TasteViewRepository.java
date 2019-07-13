package org.multilinguals.enterprise.cmrs.query.taste;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TasteViewRepository extends MongoRepository<TasteView, String> {
}
