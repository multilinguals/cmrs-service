package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.infrastructure.dto.QueryResponse;
import org.multilinguals.enterprise.cmrs.query.taste.TasteView;
import org.multilinguals.enterprise.cmrs.query.taste.TasteViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TasteQueryController {
    private TasteViewRepository tasteViewRepository;

    @Autowired
    public TasteQueryController(TasteViewRepository tasteViewRepository) {
        this.tasteViewRepository = tasteViewRepository;
    }

    @GetMapping("/user/get-all-taste")
    @PreAuthorize("isAuthenticated()")
    public QueryResponse<List<TasteView>> queryAllDishType() {
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        List<TasteView> tasteViews = this.tasteViewRepository.findAll(sort);

        return new QueryResponse<>(tasteViews);
    }
}
