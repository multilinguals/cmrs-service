package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.*;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.constant.aggregate.menutype.DefaultMenuItemType;
import org.multilinguals.enterprise.cmrs.dto.aggregate.AggregateCreatedDTO;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.*;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.CMRSHTTPException;
import org.multilinguals.enterprise.cmrs.query.dishtype.DishTypeViewRepository;
import org.multilinguals.enterprise.cmrs.query.menuitem.SetMenuItemView;
import org.multilinguals.enterprise.cmrs.query.menuitem.SetMenuItemViewRepository;
import org.multilinguals.enterprise.cmrs.query.menuitem.SingleMenuItemView;
import org.multilinguals.enterprise.cmrs.query.menuitem.SingleMenuItemViewRepository;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeView;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeViewRepository;
import org.multilinguals.enterprise.cmrs.query.restaurant.RestaurantDetailsViewRepository;
import org.multilinguals.enterprise.cmrs.query.taste.TasteViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RestaurantCommandController {
    @Resource
    private CommandGateway commandGateway;

    private RestaurantDetailsViewRepository restaurantDetailsViewRepository;

    private SingleMenuItemViewRepository singleMenuItemViewRepository;

    private SetMenuItemViewRepository setMenuItemViewRepository;

    private MenuItemTypeViewRepository menuItemTypeViewRepository;

    private DishTypeViewRepository dishTypeViewRepository;

    private TasteViewRepository tasteViewRepository;

    @Autowired
    public RestaurantCommandController(RestaurantDetailsViewRepository restaurantDetailsViewRepository, SingleMenuItemViewRepository singleMenuItemViewRepository, SetMenuItemViewRepository setMenuItemViewRepository, MenuItemTypeViewRepository menuItemTypeViewRepository, DishTypeViewRepository dishTypeViewRepository, TasteViewRepository tasteViewRepository) {
        this.restaurantDetailsViewRepository = restaurantDetailsViewRepository;
        this.singleMenuItemViewRepository = singleMenuItemViewRepository;
        this.setMenuItemViewRepository = setMenuItemViewRepository;
        this.menuItemTypeViewRepository = menuItemTypeViewRepository;
        this.dishTypeViewRepository = dishTypeViewRepository;
        this.tasteViewRepository = tasteViewRepository;
    }

    @PostMapping("/create-restaurant")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public AggregateCreatedDTO<String> createRestaurant(@RequestBody CreateRestaurantCommand command, @RequestAttribute String reqSenderId) {
        command.setCreatorId(new UserId(reqSenderId));
        RestaurantId restaurantId = commandGateway.sendAndWait(command);
        return new AggregateCreatedDTO<>(restaurantId.getIdentifier());
    }

    @PostMapping("/update-details-of-restaurant/{restId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public void createRestaurant(@PathVariable String restId, @RequestBody UpdateRestaurantDetailsCommand command, HttpServletResponse response) {
        try {
            this.restaurantDetailsViewRepository.findById(restId).orElseThrow(RestaurantNotExistException::new);
            command.setId(new RestaurantId(restId));
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (RestaurantNotExistException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping("/create-single-menu-item")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public AggregateCreatedDTO<String> createSingleMenuItem(@RequestBody CreateSingleMenuItemCommand command) throws MenuItemTypeNotExistException {
        try {
            this.restaurantDetailsViewRepository.findById(command.getRestaurantId().getIdentifier()).orElseThrow(RestaurantNotExistException::new);
            this.dishTypeViewRepository.findById(command.getDishTypeId().getIdentifier()).orElseThrow(DishTypeNotExistException::new);
            if (command.getTasteId() != null) {
                this.tasteViewRepository.findById(command.getTasteId().getIdentifier()).orElseThrow(TasteNotExistException::new);
            }
        } catch (RestaurantNotExistException | DishTypeNotExistException | TasteNotExistException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }

        MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findOne(Example.of(new MenuItemTypeView(null, DefaultMenuItemType.SINGLE, null)))
                .orElseThrow(MenuItemTypeNotExistException::new);

        command.setMenuItemTypeId(new MenuItemTypeId(menuItemTypeView.getId()));

        MenuItemId menuItemId = commandGateway.sendAndWait(command);
        return new AggregateCreatedDTO<>(menuItemId.getIdentifier());
    }

    @PostMapping("/update-restaurant/{restId}/single-menu-item/{itemId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public void updateSingleMenuItem(@PathVariable String restId, @PathVariable String itemId, @RequestBody UpdateSingleMenuItemCommand command, HttpServletResponse response) {
        try {
            this.restaurantDetailsViewRepository.findById(restId).orElseThrow(RestaurantNotExistException::new);
            SingleMenuItemView singleMenuItemView = this.singleMenuItemViewRepository.findById(itemId).orElseThrow(MenuItemNotExistException::new);

            if (!restId.equals(singleMenuItemView.getRestaurantId())) {
                throw new RestaurantNotMatchMenuItemException();
            }

            MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findOne(Example.of(new MenuItemTypeView(null, DefaultMenuItemType.SINGLE, null)))
                    .orElseThrow(MenuItemTypeNotExistException::new);

            if (!singleMenuItemView.getMenuItemTypeId().equals(menuItemTypeView.getId())) {
                throw new SingleMenuItemRequiredException();
            }
        } catch (RestaurantNotExistException | MenuItemNotExistException | RestaurantNotMatchMenuItemException | MenuItemTypeNotExistException | SingleMenuItemRequiredException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }

        command.setRestaurantId(new RestaurantId(restId));
        command.setId(new MenuItemId(itemId));
        commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/create-set-menu-item")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public AggregateCreatedDTO<String> createSetMenuItem(@RequestBody CreateSetMenuItemCommand command) throws MenuItemTypeNotExistException {
        try {
            this.restaurantDetailsViewRepository.findById(command.getRestaurantId().getIdentifier()).orElseThrow(RestaurantNotExistException::new);
        } catch (RestaurantNotExistException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }

        MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findOne(Example.of(new MenuItemTypeView(null, DefaultMenuItemType.SET, null)))
                .orElseThrow(MenuItemTypeNotExistException::new);

        command.setMenuItemTypeId(new MenuItemTypeId(menuItemTypeView.getId()));

        MenuItemId menuItemId = commandGateway.sendAndWait(command);
        return new AggregateCreatedDTO<>(menuItemId.getIdentifier());
    }

    @PostMapping("/update-restaurant/{restId}/set-menu-item/{itemId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public void updateSetMenuItem(@PathVariable String restId, @PathVariable String itemId, @RequestBody UpdateSetMenuItemCommand command, HttpServletResponse response) {
        try {
            this.restaurantDetailsViewRepository.findById(restId).orElseThrow(RestaurantNotExistException::new);
            SetMenuItemView setMenuItemView = this.setMenuItemViewRepository.findById(itemId).orElseThrow(MenuItemNotExistException::new);

            if (!restId.equals(setMenuItemView.getRestaurantId())) {
                throw new RestaurantNotMatchMenuItemException();
            }

            MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findOne(Example.of(new MenuItemTypeView(null, DefaultMenuItemType.SET, null)))
                    .orElseThrow(MenuItemTypeNotExistException::new);

            if (!setMenuItemView.getMenuItemTypeId().equals(menuItemTypeView.getId())) {
                throw new SetMenuItemRequiredException();
            }
        } catch (RestaurantNotExistException | MenuItemNotExistException | RestaurantNotMatchMenuItemException | MenuItemTypeNotExistException | SetMenuItemRequiredException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }

        command.setRestaurantId(new RestaurantId(restId));
        command.setId(new MenuItemId(itemId));

        commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/add-items-to-set-menu-item/{setMenuItemId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public void addItemsToSetMenuItem(@PathVariable String setMenuItemId, @RequestBody AddItemsToSetMenuItemCommand command, HttpServletResponse response) {
        // TODO 需要校验Item和Set是一个Rest
        command.setSetMenuItemId(new MenuItemId(setMenuItemId));
        this.commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/remove-items-from-set-menu-item/{setMenuItemId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public void removeItemsFromSetMenuItem(@PathVariable String setMenuItemId, @RequestBody RemoveItemsFromMenuItemCommand command, HttpServletResponse response) {
        command.setSetMenuItemId(new MenuItemId(setMenuItemId));
        this.commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @PostMapping("/delete-single-menu-item/{menuItemId}/of-restaurant/{restId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public void deleteSingleMenuItem(@PathVariable String menuItemId, @PathVariable String restId, HttpServletResponse response) {
        try {
            this.singleMenuItemViewRepository.findOne(Example.of(new SingleMenuItemView(menuItemId, restId))).orElseThrow(MenuItemNotExistException::new);

            this.commandGateway.sendAndWait(new DeleteSingleMenuItemCommand(new RestaurantId(restId), new MenuItemId(menuItemId)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (MenuItemNotExistException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping("/delete-set-menu-item/{menuItemId}/of-restaurant/{restId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public void deleteSetMenuItem(@PathVariable String menuItemId, @PathVariable String restId, HttpServletResponse response) {
        try {
            this.setMenuItemViewRepository.findOne(Example.of(new SetMenuItemView(menuItemId, restId))).orElseThrow(MenuItemNotExistException::new);

            this.commandGateway.sendAndWait(new DeleteSetMenuItemCommand(new RestaurantId(restId), new MenuItemId(menuItemId)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (MenuItemNotExistException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        }
    }
}
