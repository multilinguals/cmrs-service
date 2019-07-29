package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.infrastructure.dto.CMRSPage;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.QueryResponse;
import org.multilinguals.enterprise.cmrs.query.rbac.RoleDetailsView;
import org.multilinguals.enterprise.cmrs.query.rbac.RoleDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RoleQueryController {
    private final RoleDetailsViewRepository roleDetailsViewRepository;

    @Autowired
    public RoleQueryController(RoleDetailsViewRepository roleDetailsViewRepository) {
        this.roleDetailsViewRepository = roleDetailsViewRepository;
    }

    @GetMapping("/get-role-list")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public QueryResponse adminGetRoleList(@RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<RoleDetailsView> roleDetailsViewPage = this.roleDetailsViewRepository.findAll(PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort));

        return new QueryResponse<>(new CMRSPage<>(roleDetailsViewPage));
    }

    @GetMapping("/get-all-roles")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public QueryResponse<List<RoleDetailsView>> adminGetRoleList() {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        List<RoleDetailsView> roleDetailsViewPage = this.roleDetailsViewRepository.findAll(sort);

        return new QueryResponse<>(roleDetailsViewPage);
    }
}
