package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.infrastructure.dto.QueryResponse;
import org.multilinguals.enterprise.cmrs.query.dishtype.DishTypeView;
import org.multilinguals.enterprise.cmrs.query.dishtype.DishTypeViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DishTypeQueryController {
    private DishTypeViewRepository dishTypeViewRepository;

    @Autowired
    public DishTypeQueryController(DishTypeViewRepository dishTypeViewRepository) {
        this.dishTypeViewRepository = dishTypeViewRepository;
    }

    @GetMapping("/get-all-dish-type")
    @PreAuthorize("isAuthenticated()")
    public QueryResponse<List<DishTypeView>> queryAllDishType() {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        List<DishTypeView> dishTypeViews = this.dishTypeViewRepository.findAll(sort);

        return new QueryResponse<>(dishTypeViews);
    }
}
