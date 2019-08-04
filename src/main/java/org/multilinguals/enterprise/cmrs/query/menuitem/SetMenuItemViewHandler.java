package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SetSubItem;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.SetSubItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.*;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.MenuItemNotExistException;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.aggregate.MenuItemTypeNotExistException;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeView;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SetMenuItemViewHandler {
    private SetMenuItemViewRepository setMenuItemViewRepository;
    private MenuItemTypeViewRepository menuItemTypeViewRepository;
    private SingleMenuItemViewRepository singleMenuItemViewRepository;

    @Autowired
    public SetMenuItemViewHandler(SetMenuItemViewRepository setMenuItemViewRepository, MenuItemTypeViewRepository menuItemTypeViewRepository, SingleMenuItemViewRepository singleMenuItemViewRepository) {
        this.setMenuItemViewRepository = setMenuItemViewRepository;
        this.menuItemTypeViewRepository = menuItemTypeViewRepository;
        this.singleMenuItemViewRepository = singleMenuItemViewRepository;
    }

    @EventHandler
    public void on(SetMenuItemCreatedEvent event, @Timestamp java.time.Instant createdTime) throws MenuItemTypeNotExistException, MenuItemNotExistException {
        List<SetSubItemView> subItemViews = new ArrayList<>();

        for (SetSubItem subItem : event.getSubItems()) {
            SingleMenuItemView singleMenuItemView = this.singleMenuItemViewRepository.findById(subItem.getSingleMenuItemId().getIdentifier()).orElseThrow(MenuItemNotExistException::new);
            subItemViews.add(new SetSubItemView(subItem.getId().getIdentifier(), subItem.getQuantity(), singleMenuItemView, new Date(createdTime.toEpochMilli())));
        }

        MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findById(event.getMenuItemTypeId().getIdentifier())
                .orElseThrow(MenuItemTypeNotExistException::new);

        SetMenuItemView setMenuItemView = new SetMenuItemView(
                event.getId().getIdentifier(),
                event.getRestaurantId().getIdentifier(),
                event.getName(),
                event.getMenuItemTypeId().getIdentifier(),
                menuItemTypeView.getName(),
                event.getPrice(),
                event.getOnShelve(),
                subItemViews,
                new Date(createdTime.toEpochMilli())
        );

        this.setMenuItemViewRepository.save(setMenuItemView);
    }

    @EventHandler
    public void on(SetMenuItemUpdatedEvent event, @Timestamp java.time.Instant createdTime) throws MenuItemNotExistException {
        SetMenuItemView setMenuItemView = this.setMenuItemViewRepository.findById(event.getId().getIdentifier())
                .orElseThrow(MenuItemNotExistException::new);
        if (StringUtils.isNotEmpty(event.getName())) {
            setMenuItemView.setName(event.getName());
        }

        if (event.getPrice() != null) {
            setMenuItemView.setPrice(event.getPrice());
        }

        if (event.getOnShelve() != null) {
            setMenuItemView.setOnShelve(event.getOnShelve());
        }

        List<SetSubItemView> setSubItemViews = setMenuItemView.getSubItemViews();
        setSubItemViews.clear();

        for (SetSubItem subItem : event.getSubItems()) {
            SingleMenuItemView singleMenuItemView = this.singleMenuItemViewRepository.findById(subItem.getSingleMenuItemId().getIdentifier()).orElseThrow(MenuItemNotExistException::new);
            setMenuItemView.addSubItem(new SetSubItemView(subItem.getId().getIdentifier(), subItem.getQuantity(), singleMenuItemView, new Date(createdTime.toEpochMilli())));
        }

        setMenuItemView.setUpdatedAt(new Date(createdTime.toEpochMilli()));

        this.setMenuItemViewRepository.save(setMenuItemView);
    }

    @EventHandler
    public void on(ItemsAddedToSetMenuItemEvent event, @Timestamp java.time.Instant createdTime) throws MenuItemNotExistException {
        SetMenuItemView setMenuItemView = this.setMenuItemViewRepository.findById(event.getSetMenuItemId().getIdentifier())
                .orElseThrow(MenuItemNotExistException::new);

        setMenuItemView.setPrice(event.getPrice());

        for (SetSubItem subItem : event.getSubItems()) {
            SingleMenuItemView singleMenuItemView = this.singleMenuItemViewRepository.findById(subItem.getSingleMenuItemId().getIdentifier()).orElseThrow(MenuItemNotExistException::new);
            setMenuItemView.addSubItem(new SetSubItemView(subItem.getId().getIdentifier(), subItem.getQuantity(), singleMenuItemView, new Date(createdTime.toEpochMilli())));
        }

        setMenuItemView.setUpdatedAt(new Date(createdTime.toEpochMilli()));

        this.setMenuItemViewRepository.save(setMenuItemView);
    }

    @EventHandler
    public void on(ItemsRemovedFromMenuItemEvent event, @Timestamp java.time.Instant createdTime) throws MenuItemNotExistException {
        SetMenuItemView setMenuItemView = this.setMenuItemViewRepository.findById(event.getSetMenuItemId().getIdentifier())
                .orElseThrow(MenuItemNotExistException::new);

        setMenuItemView.setPrice(event.getPrice());

        for (SetSubItemId subItemId : event.getSubItemIdList()) {
            setMenuItemView.removeSubItem(subItemId.getIdentifier());
        }

        setMenuItemView.setUpdatedAt(new Date(createdTime.toEpochMilli()));

        this.setMenuItemViewRepository.save(setMenuItemView);
    }

    @EventHandler
    public void on(SetMenuItemDeletedEvent event) {
        this.setMenuItemViewRepository.findById(event.getMenuItemId().getIdentifier())
                .ifPresent(setMenuItemView -> this.setMenuItemViewRepository.delete(setMenuItemView));
    }
}
