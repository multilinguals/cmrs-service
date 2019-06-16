package org.multilinguals.enterprise.cmrs.query.rbac;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleDetailsViewRepository extends MongoRepository<RoleDetailsView, String> {
}
