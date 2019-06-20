package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.infrastructure.dto.QueryResponse;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsView;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserQueryController {
    private final UserDetailsViewRepository userDetailsViewRepository;

    @Autowired
    public UserQueryController(UserDetailsViewRepository userDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
    }

    /**
     * 用户查询自己的详细信息
     *
     * @param reqSenderId 请求发起人的用户ID
     * @return 详细信息
     */
    @GetMapping("/user/self-details")
    @PreAuthorize("isAuthenticated()")
    public QueryResponse<UserDetailsView> signUp(@RequestAttribute String reqSenderId) {
        Optional<UserDetailsView> userDetailsView = this.userDetailsViewRepository.findById(reqSenderId);

        return userDetailsView.map(QueryResponse::new).orElseGet(() -> new QueryResponse<>(null));
    }
}
