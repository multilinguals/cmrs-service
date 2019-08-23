package org.multilinguals.enterprise.cmrs.query.mrgroup;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupMemberViewRepository extends MongoRepository<GroupMemberView, String> {
}
