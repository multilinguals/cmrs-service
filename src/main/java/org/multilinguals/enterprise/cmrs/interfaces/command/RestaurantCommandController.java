package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.RestaurantId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.*;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.TasteId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;
import org.multilinguals.enterprise.cmrs.constant.aggregate.menutype.DefaultMenuItemType;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.restaurant.CreateRestaurantDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.restaurant.CreateSingleMenuItemDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.restaurant.UpdateRestaurantDetailsDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.command.restaurant.UpdateSingleMenuItemDTO;
import org.multilinguals.enterprise.cmrs.interfaces.dto.common.AggregateCreatedDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public AggregateCreatedDTO<String> createRestaurant(@RequestBody @Validated CreateRestaurantDTO dto, @RequestAttribute String reqSenderId) {
        RestaurantId restaurantId = commandGateway.sendAndWait(new CreateRestaurantCommand(dto.getName(), dto.getDescription(), new UserId(reqSenderId)));
        return new AggregateCreatedDTO<>(restaurantId.getIdentifier());
    }

    @PostMapping("/update-details-of-restaurant/{restId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createRestaurant(@PathVariable String restId, @RequestBody @Validated UpdateRestaurantDetailsDTO dto) throws BizException {
        try {
            commandGateway.sendAndWait(new UpdateRestaurantDetailsCommand(new RestaurantId(restId), dto.getName(), dto.getDescription()));
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.RESTAURANT_NOT_EXISTED);
        }
    }

    @PostMapping("/create-single-menu-item")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public AggregateCreatedDTO<String> createSingleMenuItem(@RequestBody @Validated CreateSingleMenuItemDTO dto) throws BizException {
        MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findOne(Example.of(new MenuItemTypeView(null, DefaultMenuItemType.SINGLE, null)))
                .orElseThrow(() -> new BizException(BizErrorCode.MENU_ITEM_NOT_EXISTED));

        TasteId tasteId = dto.getTasteId() != null ? new TasteId(dto.getTasteId()) : null;

        MenuItemId menuItemId = commandGateway.sendAndWait(new CreateSingleMenuItemCommand(
                new RestaurantId(dto.getRestaurantId()),
                dto.getName(),
                new MenuItemTypeId(menuItemTypeView.getId()),
                new DishTypeId(dto.getDishTypeId()),
                tasteId,
                dto.getPrice()
        ));
        return new AggregateCreatedDTO<>(menuItemId.getIdentifier());
    }

    @PostMapping("/update-restaurant/{restId}/single-menu-item/{itemId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSingleMenuItem(@PathVariable String restId, @PathVariable String itemId, @RequestBody @Validated UpdateSingleMenuItemDTO dto) {
        commandGateway.sendAndWait(new UpdateSingleMenuItemCommand(
                new RestaurantId(restId),
                new MenuItemId(itemId),
                dto.getName(),
                dto.getDishTypeId() != null ? new DishTypeId(dto.getDishTypeId()) : null,
                dto.getTasteId() != null ? new TasteId(dto.getTasteId()) : null,
                dto.getPrice(),
                dto.getOnShelve()));
    }

    @PostMapping("/create-set-menu-item")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    public AggregateCreatedDTO<String> createSetMenuItem(@RequestBody CreateSetMenuItemCommand command) throws BizException {
        MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findOne(Example.of(new MenuItemTypeView(null, DefaultMenuItemType.SET, null)))
                .orElseThrow(() -> new BizException(BizErrorCode.MENU_ITEM_NOT_EXISTED));

        command.setMenuItemTypeId(new MenuItemTypeId(menuItemTypeView.getId()));

        MenuItemId menuItemId = commandGateway.sendAndWait(command);
        return new AggregateCreatedDTO<>(menuItemId.getIdentifier());
    }

    @PostMapping("/update-restaurant/{restId}/set-menu-item/{itemId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSetMenuItem(@PathVariable String restId, @PathVariable String itemId, @RequestBody UpdateSetMenuItemCommand command) {
        command.setRestaurantId(new RestaurantId(restId));
        command.setId(new MenuItemId(itemId));
        commandGateway.sendAndWait(command);
    }

    @PostMapping("/add-items-to-set-menu-item/{setMenuItemId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItemsToSetMenuItem(@PathVariable String setMenuItemId, @RequestBody AddItemsToSetMenuItemCommand command) {
        // TODO 需要校验Item和Set是一个Rest
        command.setSetMenuItemId(new MenuItemId(setMenuItemId));
        this.commandGateway.sendAndWait(command);
    }

    @PostMapping("/remove-items-from-set-menu-item/{setMenuItemId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItemsFromSetMenuItem(@PathVariable String setMenuItemId, @RequestBody RemoveItemsFromMenuItemCommand command) {
        command.setSetMenuItemId(new MenuItemId(setMenuItemId));
        this.commandGateway.sendAndWait(command);
    }

    @PostMapping("/delete-single-menu-item/{menuItemId}/of-restaurant/{restId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSingleMenuItem(@PathVariable String menuItemId, @PathVariable String restId) throws BizException {
        this.singleMenuItemViewRepository.findOne(Example.of(new SingleMenuItemView(menuItemId, restId)))
                .orElseThrow(() -> new BizException(BizErrorCode.MENU_ITEM_NOT_EXISTED));

        this.commandGateway.sendAndWait(new DeleteSingleMenuItemCommand(new RestaurantId(restId), new MenuItemId(menuItemId)));
    }

    @PostMapping("/delete-set-menu-item/{menuItemId}/of-restaurant/{restId}")
    @PreAuthorize("hasAnyRole('ROLE_REST_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSetMenuItem(@PathVariable String menuItemId, @PathVariable String restId) throws BizException {
        this.setMenuItemViewRepository.findOne(Example.of(new SetMenuItemView(menuItemId, restId)))
                .orElseThrow(() -> new BizException(BizErrorCode.MENU_ITEM_NOT_EXISTED));

        this.commandGateway.sendAndWait(new DeleteSetMenuItemCommand(new RestaurantId(restId), new MenuItemId(menuItemId)));
    }
}
