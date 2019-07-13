package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.CreateRestaurantCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.CreateSingleMenuItemCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.constant.aggregate.menutype.DefaultMenuItemType;
import org.multilinguals.enterprise.cmrs.dto.aggregate.AggregateCreatedDTO;
import org.multilinguals.enterprise.cmrs.infrastructure.dto.CommandResponse;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.DishTypeNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.MenuItemTypeNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.RestaurantNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.TasteNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.multilinguals.enterprise.cmrs.query.dishtype.DishTypeViewRepository;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeView;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeViewRepository;
import org.multilinguals.enterprise.cmrs.query.restaurant.RestaurantDetailsViewRepository;
import org.multilinguals.enterprise.cmrs.query.taste.TasteViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RestaurantCommandController {
    @Resource
    private CommandGateway commandGateway;

    private RestaurantDetailsViewRepository restaurantDetailsViewRepository;

    private MenuItemTypeViewRepository menuItemTypeViewRepository;

    private DishTypeViewRepository dishTypeViewRepository;

    private TasteViewRepository tasteViewRepository;

    @Autowired
    public RestaurantCommandController(RestaurantDetailsViewRepository restaurantDetailsViewRepository, MenuItemTypeViewRepository menuItemTypeViewRepository, DishTypeViewRepository dishTypeViewRepository, TasteViewRepository tasteViewRepository) {
        this.restaurantDetailsViewRepository = restaurantDetailsViewRepository;
        this.menuItemTypeViewRepository = menuItemTypeViewRepository;
        this.dishTypeViewRepository = dishTypeViewRepository;
        this.tasteViewRepository = tasteViewRepository;
    }

    @PostMapping("/admin/create-restaurant")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public CommandResponse<AggregateCreatedDTO<String>> createRestaurant(@RequestBody CreateRestaurantCommand command, @RequestAttribute String reqSenderId, HttpServletResponse response) {
        command.setCreatorId(new UserId(reqSenderId));
        RestaurantId restaurantId = commandGateway.sendAndWait(command);
        return new CommandResponse<>(new AggregateCreatedDTO<>(restaurantId.getIdentifier()));
    }

    @PostMapping("/admin/create-single-menu-item")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public CommandResponse<AggregateCreatedDTO<String>> createSingleMenuItem(@RequestBody CreateSingleMenuItemCommand command, HttpServletResponse response) throws MenuItemTypeNotExistException {
        try {
            this.restaurantDetailsViewRepository.findById(command.getRestaurantId().getIdentifier()).orElseThrow(RestaurantNotExistException::new);
            this.dishTypeViewRepository.findById(command.getDishTypeId().getIdentifier()).orElseThrow(DishTypeNotExistException::new);
            this.tasteViewRepository.findById(command.getTasteId().getIdentifier()).orElseThrow(TasteNotExistException::new);
        } catch (RestaurantNotExistException | DishTypeNotExistException | TasteNotExistException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }

        MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findOne(Example.of(new MenuItemTypeView(null, DefaultMenuItemType.SINGLE, null)))
                .orElseThrow(MenuItemTypeNotExistException::new);

        command.setMenuItemTypeId(new MenuItemTypeId(menuItemTypeView.getId()));

        MenuItemId menuItemId = commandGateway.sendAndWait(command);
        return new CommandResponse<>(new AggregateCreatedDTO<>(menuItemId.getIdentifier()));
    }
}
