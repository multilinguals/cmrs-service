package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.infrastructure.dto.QueryResponse;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeView;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuItemTypeQueryController {
    private MenuItemTypeViewRepository menuItemTypeViewRepository;

    @Autowired
    public MenuItemTypeQueryController(MenuItemTypeViewRepository menuItemTypeViewRepository) {
        this.menuItemTypeViewRepository = menuItemTypeViewRepository;
    }

    @GetMapping("/user/get-all-menu-item-type")
    @PreAuthorize("isAuthenticated()")
    public QueryResponse<List<MenuItemTypeView>> queryAllDishType() {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        List<MenuItemTypeView> menuItemTypeViews = this.menuItemTypeViewRepository.findAll(sort);

        return new QueryResponse<>(menuItemTypeViews);
    }
}
