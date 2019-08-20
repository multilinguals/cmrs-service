package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.dishtype.DishTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.menuitemtype.MenuItemTypeId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.*;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.*;
import org.multilinguals.enterprise.cmrs.command.aggregate.taste.TasteId;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Restaurant {
    @AggregateIdentifier
    private RestaurantId id;

    private String name;

    private String description;

    private UserId creatorId;

    @AggregateMember
    private Map<MenuItemId, MenuItem> menu = new HashMap<>();

    protected Restaurant() {
    }

    @CommandHandler
    public Restaurant(CreateRestaurantCommand command) {
        apply(new RestaurantCreatedEvent(new RestaurantId(), command.getName(), command.getDescription(), command.getCreatorId()));
    }

    @CommandHandler
    public void handler(UpdateRestaurantDetailsCommand command) {
        apply(new RestaurantDetailsUpdatedEvent(command.getId(), command.getName(), command.getDescription()));
    }

    public MenuItemId createSingleMenuItem(String name, MenuItemTypeId menuItemTypeId, DishTypeId dishTypeId, TasteId tasteId, BigDecimal price) {
        MenuItemId menuItemId = new MenuItemId();
        apply(new SingleMenuItemCreatedEvent(menuItemId, this.id, name, menuItemTypeId, dishTypeId, tasteId, price));
        return menuItemId;
    }

    public void updateSingleMenuItem(MenuItemId menuItemId, String name, DishTypeId dishTypeId, TasteId tasteId, BigDecimal price, Boolean onShelve) {
        apply(new SingleMenuItemUpdatedEvent(this.id, menuItemId, name, dishTypeId, tasteId, price, onShelve));
    }

    public MenuItemId createSetMenuItem(String name, MenuItemTypeId menuItemTypeId, BigDecimal price, List<SetSubItem> subItems) {
        MenuItemId menuItemId = new MenuItemId();

        for (SetSubItem subItem : subItems) {
            subItem.setId(new SetSubItemId());
        }

        apply(new SetMenuItemCreatedEvent(menuItemId, this.id, name, menuItemTypeId, price, subItems, Boolean.FALSE));
        return menuItemId;
    }

    public void updateSetMenuItem(MenuItemId menuItemId, String name, BigDecimal price, Boolean onShelve, List<SetSubItem> subItems) {
        for (SetSubItem subItem : subItems) {
            if (subItem.getId() == null) {
                subItem.setId(new SetSubItemId());
            }
        }

        apply(new SetMenuItemUpdatedEvent(this.id, menuItemId, name, price, onShelve, subItems));
    }

    @CommandHandler
    public void handler(AddItemsToSetMenuItemCommand command) {
        List<SetSubItem> subItems = command.getSubItems();

        for (SetSubItem subItem : subItems) {
            subItem.setId(new SetSubItemId());
            subItem.setSingleMenuItemId(subItem.getSingleMenuItemId());
        }

        apply(new ItemsAddedToSetMenuItemEvent(command.getRestaurantId(), command.getSetMenuItemId(), command.getPrice(), subItems));
    }

    @CommandHandler
    public void handler(RemoveItemsFromMenuItemCommand command) {
        apply(new ItemsRemovedFromMenuItemEvent(command.getRestaurantId(), command.getSetMenuItemId(), command.getPrice(), command.getSubItemIdList()));
    }

    @CommandHandler
    public void handler(DeleteSingleMenuItemCommand command) {
        apply(new SingleMenuItemDeletedEvent(command.getRestaurantId(), command.getMenuItemId()));
    }

    @CommandHandler
    public void handler(DeleteSetMenuItemCommand command) {
        apply(new SetMenuItemDeletedEvent(command.getRestaurantId(), command.getMenuItemId()));
    }

    @EventSourcingHandler
    public void on(RestaurantCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.creatorId = event.getCreatorId();
    }

    @EventSourcingHandler
    public void on(RestaurantDetailsUpdatedEvent event) {
        this.name = event.getName();
        if (event.getDescription() != null) {
            this.description = event.getDescription();
        }
    }

    @EventSourcingHandler
    public void on(SingleMenuItemCreatedEvent event) {
        SingleMenuItem singleMenuItem = new SingleMenuItem(event.getId(), event.getName(), event.getMenuItemTypeId(), event.getPrice(), Boolean.FALSE, event.getDishTypeId(), event.getTasteId());

        this.menu.put(singleMenuItem.getId(), singleMenuItem);
    }

    @EventSourcingHandler
    public void on(SingleMenuItemUpdatedEvent event) {
        SingleMenuItem menuItem = (SingleMenuItem) this.menu.get(event.getId());
        if (event.getDishTypeId() != null) {
            menuItem.setDishTypeId(event.getDishTypeId());
        }

        if (event.getName() != null) {
            menuItem.setName(event.getName());
        }

        if (event.getTasteId() != null) {
            menuItem.setTasteId(event.getTasteId());
        }

        if (event.getPrice() != null) {
            menuItem.setPrice(event.getPrice());
        }

        if (event.getOnShelve() != null) {
            menuItem.setOnShelve(event.getOnShelve());
        }
    }

    @EventSourcingHandler
    public void on(SetMenuItemCreatedEvent event) {
        SetMenuItem setMenuItem = new SetMenuItem(
                event.getId(),
                event.getName(),
                event.getMenuItemTypeId(),
                event.getPrice(),
                event.getOnShelve(),
                event.getSubItems()
        );

        this.menu.put(setMenuItem.getId(), setMenuItem);
    }

    @EventSourcingHandler
    public void on(SetMenuItemUpdatedEvent event) {
        SetMenuItem menuItem = (SetMenuItem) this.menu.get(event.getId());

        if (event.getName() != null) {
            menuItem.setName(event.getName());
        }

        if (event.getPrice() != null) {
            menuItem.setPrice(event.getPrice());
        }

        if (event.getOnShelve() != null) {
            menuItem.setOnShelve(event.getOnShelve());
        }

        if (event.getSubItems() != null && event.getSubItems().size() > 0) {
            menuItem.getSubItems().clear();
            menuItem.setSubItems(event.getSubItems());
        }
    }

    @EventSourcingHandler
    public void on(ItemsAddedToSetMenuItemEvent event) {
        SetMenuItem setMenuItem = (SetMenuItem) this.menu.get(event.getSetMenuItemId());

        setMenuItem.setPrice(event.getPrice());

        for (SetSubItem subItem : event.getSubItems()) {
            setMenuItem.addSubItem(subItem);
        }
    }

    @EventSourcingHandler
    public void on(ItemsRemovedFromMenuItemEvent event) {
        SetMenuItem setMenuItem = (SetMenuItem) this.menu.get(event.getSetMenuItemId());

        setMenuItem.setPrice(event.getPrice());

        for (SetSubItemId subItem : event.getSubItemIdList()) {
            setMenuItem.removeSubItem(subItem);
        }
    }

    @EventSourcingHandler
    public void on(SingleMenuItemDeletedEvent event) {
        this.menu.remove(event.getMenuItemId());
    }

    @EventSourcingHandler
    public void on(SetMenuItemDeletedEvent event) {
        this.menu.remove(event.getMenuItemId());
    }

    public RestaurantId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserId getCreatorId() {
        return creatorId;
    }

    public Map<MenuItemId, MenuItem> getMenu() {
        return menu;
    }

    public MenuItem getMenuItemById(MenuItemId menuItemId) {
        return this.menu.get(menuItemId);
    }
}
