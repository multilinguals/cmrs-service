package org.multilinguals.enterprise.cmrs.query.mrgroup.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupMemberViewRepository extends MongoRepository<GroupMemberView, String> {
    Page<GroupMemberView> findAllByMrGroupIdAndGroupRoles(String groupId, String roleName, Pageable pageable);
}
