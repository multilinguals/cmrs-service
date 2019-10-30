package org.multilinguals.enterprise.cmrs.interfaces.query;

import org.multilinguals.enterprise.cmrs.interfaces.dto.common.CMRSPage;
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

    @GetMapping("/get-restaurant-list")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public CMRSPage<RestaurantDetailsView> adminGetRestList(@RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Page<RestaurantDetailsView> restaurantDetailsViews = this.restaurantDetailsViewRepository.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sort));
        return new CMRSPage<>(restaurantDetailsViews);
    }

    @GetMapping("/get-details-of-restaurant/{id}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public RestaurantDetailsView adminGetRestDetails(@PathVariable String id) {
        return this.restaurantDetailsViewRepository.findById(id).orElse(null);
    }

    @GetMapping("/get-restaurant/{restId}/single-menu-item-list")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public CMRSPage<SingleMenuItemView> adminGetSingleMenuItemList(@PathVariable String restId, @RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Page<SingleMenuItemView> menuItemViews = this.singleMenuItemViewRepository.findAll(Example.of(new SingleMenuItemView(restId)), PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sort));
        return new CMRSPage<>(menuItemViews);
    }

    @GetMapping("/get-restaurant/{restId}/all-single-menu-items")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public List<SingleMenuItemView> adminGetSingleMenuItemList(@PathVariable String restId) {
        return this.singleMenuItemViewRepository.findAll(Example.of(new SingleMenuItemView(restId)));
    }

    @GetMapping("/get-restaurant/{restId}/set-menu-item-list")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public CMRSPage<SetMenuItemView> adminGetSetMenuItemList(@PathVariable String restId, @RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Page<SetMenuItemView> menuItemViews = this.setMenuItemViewRepository.findAll(Example.of(new SetMenuItemView(restId)), PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sort));
        return new CMRSPage<>(menuItemViews);
    }

    @GetMapping("/find-restaurant")
    public CMRSPage<RestaurantDetailsView> findRestaurant(@RequestParam String name, @RequestParam(defaultValue = "0", required = false) String page, @RequestParam(defaultValue = "20", required = false) String size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createdAt");
        Page<RestaurantDetailsView> restaurantDetailsViewPage = this.restaurantDetailsViewRepository.findByNameLike(name, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sort));
        return new CMRSPage<>(restaurantDetailsViewPage);
    }
}
