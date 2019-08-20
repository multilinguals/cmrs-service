package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.CMRSPage;
import org.multilinguals.enterprise.cmrs.interfaces.dto.query.FoundUserDTO;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsView;
import org.multilinguals.enterprise.cmrs.query.user.UserDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
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
    @GetMapping("/get-self-details")
    @PreAuthorize("isAuthenticated()")
    public UserDetailsView querySelfDetails(@RequestAttribute String reqSenderId) {
        Optional<UserDetailsView> userDetailsView = this.userDetailsViewRepository.findById(reqSenderId);

        return userDetailsView.orElseGet(() -> null);
    }

    /**
     * 管理员查询用户列表
     *
     * @param page 当前页码
     * @param size 页面大小
     * @return 用户列表
     */
    @GetMapping("/get-user-list")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public CMRSPage<UserDetailsView> queryUserList(@RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Page<UserDetailsView> userDetailsViewPage = this.userDetailsViewRepository.findAll(PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort));
        return new CMRSPage<>(userDetailsViewPage);
    }

    /**
     * 管理员查看其他用户详情
     *
     * @param id 用户id
     * @return 用户详情
     */
    @GetMapping("/get-user-details/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public UserDetailsView queryUserDetails(@PathVariable String id) throws BizException {
        return this.userDetailsViewRepository.findById(id)
                .orElseThrow(() -> new BizException(HttpServletResponse.SC_NOT_FOUND, BizErrorCode.USER_NOT_EXISTED));
    }

    @GetMapping("/find-user")
    @PreAuthorize("isAuthenticated()")
    public List<FoundUserDTO> findUser(@RequestParam String realName) {
        List<FoundUserDTO> foundUsers = new ArrayList<>();

        this.userDetailsViewRepository.findByRealNameLike(
                realName,
                PageRequest.of(0, 5)
        ).forEach(userDetailsView -> {
            foundUsers.add(new FoundUserDTO(userDetailsView.getId(), userDetailsView.getRealName()));
        });

        return foundUsers;
    }
}
