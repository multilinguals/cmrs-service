package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.MenuItemId;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.ItemsAddedToSetMenuItemEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.ItemsRemovedFromMenuItemEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.SetMenuItemCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.SetMenuItemUpdatedEvent;
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
    public void on(SetMenuItemCreatedEvent event, @Timestamp java.time.Instant createdTime) throws MenuItemTypeNotExistException {
        List<String> singleItemIdList = new ArrayList<>();

        for (MenuItemId id : event.getSingleMenuItemIdList()) {
            singleItemIdList.add(id.getIdentifier());
        }

        List<SingleMenuItemView> singleMenuItemViews = new ArrayList<>();
        Iterable<SingleMenuItemView> singleMenuItemViewIterable = this.singleMenuItemViewRepository.findAllById(singleItemIdList);
        singleMenuItemViewIterable.forEach(singleMenuItemViews::add);

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
                singleMenuItemViews,
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

        setMenuItemView.setUpdatedAt(new Date(createdTime.toEpochMilli()));

        this.setMenuItemViewRepository.save(setMenuItemView);
    }

    @EventHandler
    public void on(ItemsAddedToSetMenuItemEvent event, @Timestamp java.time.Instant createdTime) throws MenuItemNotExistException {
        SetMenuItemView setMenuItemView = this.setMenuItemViewRepository.findById(event.getSetMenuItemId().getIdentifier())
                .orElseThrow(MenuItemNotExistException::new);

        for (MenuItemId id : event.getSingleItemsIdList()) {
            SingleMenuItemView singleMenuItemView = this.singleMenuItemViewRepository.findById(id.getIdentifier())
                    .orElseThrow(MenuItemNotExistException::new);

            setMenuItemView.addSingleMenuItem(singleMenuItemView);
        }

        setMenuItemView.setUpdatedAt(new Date(createdTime.toEpochMilli()));

        this.setMenuItemViewRepository.save(setMenuItemView);
    }

    @EventHandler
    public void on(ItemsRemovedFromMenuItemEvent event, @Timestamp java.time.Instant createdTime) throws MenuItemNotExistException {
        SetMenuItemView setMenuItemView = this.setMenuItemViewRepository.findById(event.getSetMenuItemId().getIdentifier())
                .orElseThrow(MenuItemNotExistException::new);

        for (MenuItemId id : event.getSingleItemsIdList()) {
            SingleMenuItemView singleMenuItemView = this.singleMenuItemViewRepository.findById(id.getIdentifier())
                    .orElseThrow(MenuItemNotExistException::new);

            setMenuItemView.removeSingleMenuItem(singleMenuItemView);
        }

        setMenuItemView.setUpdatedAt(new Date(createdTime.toEpochMilli()));

        this.setMenuItemViewRepository.save(setMenuItemView);
    }
}
