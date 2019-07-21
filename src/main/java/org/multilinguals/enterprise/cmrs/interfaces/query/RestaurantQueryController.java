package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.infrastructure.dto.CMRSPage;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.QueryResponse;
import org.multilinguals.enterprise.cmrs.query.menuitem.SetMenuItemView;
import org.multilinguals.enterprise.cmrs.query.menuitem.SetMenuItemViewRepository;
import org.multilinguals.enterprise.cmrs.query.menuitem.SingleMenuItemView;
import org.multilinguals.enterprise.cmrs.query.menuitem.SingleMenuItemViewRepository;
import org.multilinguals.enterprise.cmrs.query.restaurant.RestaurantDetailsView;
import org.multilinguals.enterprise.cmrs.query.restaurant.RestaurantDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantQueryController {
    private final RestaurantDetailsViewRepository restaurantDetailsViewRepository;

    private final SingleMenuItemViewRepository singleMenuItemViewRepository;

    private final SetMenuItemViewRepository setMenuItemViewRepository;

    @Autowired
    public RestaurantQueryController(RestaurantDetailsViewRepository restaurantDetailsViewRepository, SingleMenuItemViewRepository singleMenuItemViewRepository, SetMenuItemViewRepository setMenuItemViewRepository) {
        this.restaurantDetailsViewRepository = restaurantDetailsViewRepository;
        this.singleMenuItemViewRepository = singleMenuItemViewRepository;
        this.setMenuItemViewRepository = setMenuItemViewRepository;
    }

    @GetMapping("admin/get-restaurant-list")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public QueryResponse<CMRSPage<RestaurantDetailsView>> adminGetRestList(@RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Page<RestaurantDetailsView> restaurantDetailsViews = this.restaurantDetailsViewRepository.findAll(PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort));
        return new QueryResponse<>(new CMRSPage<>(restaurantDetailsViews));
    }

    @GetMapping("admin/get-restaurant-details/{id}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public QueryResponse<RestaurantDetailsView> adminGetRestDetails(@PathVariable String id) {
        RestaurantDetailsView restaurantDetailsView = this.restaurantDetailsViewRepository.findById(id).orElse(null);
        return new QueryResponse<>(restaurantDetailsView);
    }

    @GetMapping("admin/get-restaurant/{restId}/single-menu-item-list")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public QueryResponse<CMRSPage<SingleMenuItemView>> adminGetSingleMenuItemList(@PathVariable String restId, @RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Page<SingleMenuItemView> menuItemViews = this.singleMenuItemViewRepository.findAll(Example.of(new SingleMenuItemView(restId)), PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort));
        return new QueryResponse<>(new CMRSPage<>(menuItemViews));
    }

    @GetMapping("admin/get-restaurant/{restId}/all-single-menu-items")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public QueryResponse<List<SingleMenuItemView>> adminGetSingleMenuItemList(@PathVariable String restId) {
        List<SingleMenuItemView> menuItemViews = this.singleMenuItemViewRepository.findAll(Example.of(new SingleMenuItemView(restId)));
        return new QueryResponse<>(menuItemViews);
    }

    @GetMapping("admin/get-restaurant/{restId}/set-menu-item-list")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public QueryResponse<CMRSPage<SetMenuItemView>> adminGetSetMenuItemList(@PathVariable String restId, @RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Page<SetMenuItemView> menuItemViews = this.setMenuItemViewRepository.findAll(Example.of(new SetMenuItemView(restId)), PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), sort));
        return new QueryResponse<>(new CMRSPage<>(menuItemViews));
    }
}
