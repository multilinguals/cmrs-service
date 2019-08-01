package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.infrastructure.dto.CMRSPage;
import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
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

import javax.annotation.Resource;
import java.util.List;


@RestController
public class RoleQueryController {
    @Resource
    private I18Translator i18Translator;

    private final RoleDetailsViewRepository roleDetailsViewRepository;

    @Autowired
    public RoleQueryController(RoleDetailsViewRepository roleDetailsViewRepository) {
        this.roleDetailsViewRepository = roleDetailsViewRepository;
    }

    @GetMapping("/get-role-list")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public CMRSPage<RoleDetailsView> adminGetRoleList(@RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<RoleDetailsView> roleDetailsViewPage = this.roleDetailsViewRepository.findAll(PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort));

        return new CMRSPage<>(roleDetailsViewPage);
    }

    @GetMapping("/get-all-roles")
    @PreAuthorize("hasAnyRole('ROLE_USER_ADMIN','ROLE_SUPER_ADMIN')")
    public List<RoleDetailsView> adminGetRoleList() {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        List<RoleDetailsView> roleDetailsViewList = this.roleDetailsViewRepository.findAll(sort);
        roleDetailsViewList.forEach(roleDetailsView -> {
            roleDetailsView.localize(this.i18Translator);
        });
        return roleDetailsViewList;
    }
}
