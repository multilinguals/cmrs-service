package org.multilinguals.enterprise.cmrs.command.handler.restaurant;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishType;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemType;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItem;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.Restaurant;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.CreateSetMenuItemCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.CreateSingleMenuItemCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.UpdateSetMenuItemCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.UpdateSingleMenuItemCommand;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.Taste;
import org.multilinguals.enterprise.cmrs.constant.aggregate.menutype.DefaultMenuItemType;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RestaurantCommandHandler {
    @Resource
    private Repository<Restaurant> restaurantAggregateRepository;

    @Resource
    Repository<MenuItemType> menuItemTypeAggregateRepository;

    @Resource
    Repository<DishType> dishTypeAggregateRepository;

    @Resource
    Repository<Taste> tasteAggregateRepository;

    @CommandHandler
    public MenuItemId handle(CreateSingleMenuItemCommand command) throws BizException {
        Aggregate<Restaurant> restaurantAggregate = restaurantMustBeExisted(command.getRestaurantId().getIdentifier());
        menuItemTypeMustBeSingle(command.getMenuItemTypeId().getIdentifier());
        dishTypeMustBeExisted(command.getDishTypeId().getIdentifier());
        if (command.getTasteId() != null) {
            tasteMustBeExisted(command.getTasteId().getIdentifier());
        }

        return restaurantAggregate.invoke(restaurant -> restaurant.createSingleMenuItem(
                command.getName(), command.getMenuItemTypeId(), command.getDishTypeId(), command.getTasteId(), command.getPrice())
        );
    }

    @CommandHandler
    public void handle(UpdateSingleMenuItemCommand command) throws BizException {
        // 菜单项所属的餐厅必须存在
        Aggregate<Restaurant> restaurantAggregate = restaurantMustBeExisted(command.getRestaurantId().getIdentifier());

        MenuItem menuItem = restaurantMustHasItem(restaurantAggregate, command.getId());

        menuItemTypeMustBeSingle(menuItem.getMenuItemTypeId().getIdentifier());

        dishTypeMustBeExisted(command.getDishTypeId().getIdentifier());

        if (command.getTasteId() != null) {
            tasteMustBeExisted(command.getTasteId().getIdentifier());
        }

        restaurantAggregate.execute(restaurant ->
                restaurant.updateSingleMenuItem(
                        command.getId(),
                        command.getName(),
                        command.getDishTypeId(),
                        command.getTasteId(),
                        command.getPrice(),
                        command.getOnShelve()
                )
        );
    }

    @CommandHandler
    public MenuItemId handle(CreateSetMenuItemCommand command) throws BizException {
        // 菜单项所属的餐厅必须存在
        Aggregate<Restaurant> restaurantAggregate = restaurantMustBeExisted(command.getRestaurantId().getIdentifier());
        menuItemTypeMustBeSet(command.getMenuItemTypeId().getIdentifier());

        return restaurantAggregate.invoke(restaurant -> restaurant.createSetMenuItem(
                command.getName(), command.getMenuItemTypeId(), command.getPrice(), command.getSubItems()
        ));
    }

    @CommandHandler
    public void handle(UpdateSetMenuItemCommand command) throws BizException {
        // 菜单项所属的餐厅必须存在
        Aggregate<Restaurant> restaurantAggregate = restaurantMustBeExisted(command.getRestaurantId().getIdentifier());

        MenuItem menuItem = restaurantMustHasItem(restaurantAggregate, command.getId());

        menuItemTypeMustBeSet(menuItem.getMenuItemTypeId().getIdentifier());

        restaurantAggregate.execute(restaurant -> {
            restaurant.updateSetMenuItem(command.getId(), command.getName(), command.getPrice(), command.getOnShelve(), command.getSubItems());
        });
    }

    private Aggregate<Restaurant> restaurantMustBeExisted(String restaurantId) throws BizException {
        try {
            return this.restaurantAggregateRepository.load(restaurantId);
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.RESTAURANT_NOT_EXISTED);
        }
    }

    private MenuItem restaurantMustHasItem(Aggregate<Restaurant> restaurantAggregate, MenuItemId menuItemId) throws BizException {
        MenuItem menuItem = restaurantAggregate.invoke(restaurant -> restaurant.getMenuItemById(menuItemId));

        if (menuItem == null) {
            throw new BizException(BizErrorCode.REST_NOT_MATCH_MENU_ITEM);
        }

        return menuItem;
    }

    private void menuItemTypeMustBeSingle(String menuItemTypeId) throws BizException {
        try {
            Aggregate<MenuItemType> menuItemTypeAggregate = this.menuItemTypeAggregateRepository.load(menuItemTypeId);
            if (!DefaultMenuItemType.SINGLE.equals(menuItemTypeAggregate.invoke(MenuItemType::getName))) {
                throw new BizException(BizErrorCode.SINGLE_ITEM_REQUIRED);
            }
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.MENU_ITEM_TYPE_NOT_EXISTED);
        }
    }

    private void menuItemTypeMustBeSet(String menuItemTypeId) throws BizException {
        try {
            Aggregate<MenuItemType> menuItemTypeAggregate = this.menuItemTypeAggregateRepository.load(menuItemTypeId);
            if (!DefaultMenuItemType.SET.equals(menuItemTypeAggregate.invoke(MenuItemType::getName))) {
                throw new BizException(BizErrorCode.SET_ITEM_REQUIRED);
            }
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.MENU_ITEM_TYPE_NOT_EXISTED);
        }
    }

    private void dishTypeMustBeExisted(String dishTypeId) throws BizException {
        try {
            this.dishTypeAggregateRepository.load(dishTypeId);
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.DISH_TYPE_NOT_EXISTED);
        }
    }

    private void tasteMustBeExisted(String tasteId) throws BizException {
        try {
            this.tasteAggregateRepository.load(tasteId);
        } catch (AggregateNotFoundException ex) {
            throw new BizException(BizErrorCode.TASTE_NOT_EXISTED);
        }
    }
}
