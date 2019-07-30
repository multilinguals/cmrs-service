package org.multilinguals.enterprise.cmrs.command.aggregate.restaurant;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.command.*;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.*;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.UserId;

import java.util.HashMap;
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
    public MenuItemId handler(CreateSingleMenuItemCommand command) {
        MenuItemId menuItemId = new MenuItemId();
        apply(new SingleMenuItemCreatedEvent(menuItemId, this.id, command.getName(), command.getMenuItemTypeId(), command.getDishTypeId(), command.getTasteId(), command.getPrice()));
        return menuItemId;
    }

    @CommandHandler
    public void handler(UpdateSingleMenuItemCommand command) {
        apply(new SingleMenuItemUpdatedEvent(command.getRestaurantId(), command.getId(), command.getName(), command.getDishTypeId(), command.getTasteId(), command.getPrice(), command.getOnShelve()));
    }

    @CommandHandler
    public MenuItemId handler(CreateSetMenuItemCommand command) {
        MenuItemId menuItemId = new MenuItemId();
        apply(new SetMenuItemCreatedEvent(menuItemId, command.getRestaurantId(), command.getName(), command.getMenuItemTypeId(), command.getPrice(), command.getSingleItemIdList(), Boolean.FALSE));
        return menuItemId;
    }

    @CommandHandler
    public void handler(UpdateSetMenuItemCommand command) {
        apply(new SetMenuItemUpdatedEvent(command.getRestaurantId(), command.getId(), command.getName(), command.getPrice(), command.getOnShelve()));
    }

    @CommandHandler
    public void handler(AddItemsToSetMenuItemCommand command) {
        apply(new ItemsAddedToSetMenuItemEvent(command.getRestaurantId(), command.getSetMenuItemId(), command.getSingleItemsIdList()));
    }

    @CommandHandler
    public void handler(RemoveItemsFromMenuItemCommand command) {
        apply(new ItemsRemovedFromMenuItemEvent(command.getRestaurantId(), command.getSetMenuItemId(), command.getSingleItemsIdList()));
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
                Boolean.FALSE,
                event.getSingleMenuItemIdList()
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
    }

    @EventSourcingHandler
    public void on(ItemsAddedToSetMenuItemEvent event) {
        SetMenuItem setMenuItem = (SetMenuItem) this.menu.get(event.getSetMenuItemId());
        for (MenuItemId singleItemId : event.getSingleItemsIdList()) {
            setMenuItem.addSingleMenuItem(singleItemId);
        }
    }

    @EventSourcingHandler
    public void on(ItemsRemovedFromMenuItemEvent event) {
        SetMenuItem setMenuItem = (SetMenuItem) this.menu.get(event.getSetMenuItemId());
        for (MenuItemId singleItemId : event.getSingleItemsIdList()) {
            setMenuItem.removeSingleMenuItem(singleItemId);
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
}
