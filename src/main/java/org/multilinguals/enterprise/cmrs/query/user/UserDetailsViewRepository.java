package org.multilinguals.enterprise.cmrs.query.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDetailsViewRepository extends MongoRepository<UserDetailsView, String> {
    List<UserDetailsView> findByUserSessionId(String sessionId);

    Page<UserDetailsView> findByRealNameLike(String realName, Pageable pageable);
}