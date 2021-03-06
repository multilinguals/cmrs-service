package org.multilinguals.enterprise.cmrs.query.menuitem;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.SingleMenuItemCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.SingleMenuItemDeletedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.restaurant.event.SingleMenuItemUpdatedEvent;
import org.multilinguals.enterprise.cmrs.constant.result.BizErrorCode;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.http.BizException;
import org.multilinguals.enterprise.cmrs.query.dishtype.DishTypeView;
import org.multilinguals.enterprise.cmrs.query.dishtype.DishTypeViewRepository;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeView;
import org.multilinguals.enterprise.cmrs.query.menuitemtype.MenuItemTypeViewRepository;
import org.multilinguals.enterprise.cmrs.query.taste.TasteView;
import org.multilinguals.enterprise.cmrs.query.taste.TasteViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SingleMenuItemViewHandler {
    private SingleMenuItemViewRepository singleMenuItemViewRepository;

    private MenuItemTypeViewRepository menuItemTypeViewRepository;

    private DishTypeViewRepository dishTypeViewRepository;

    private TasteViewRepository tasteViewRepository;

    @Autowired
    public SingleMenuItemViewHandler(SingleMenuItemViewRepository singleMenuItemViewRepository, MenuItemTypeViewRepository menuItemTypeViewRepository, DishTypeViewRepository dishTypeViewRepository, TasteViewRepository tasteViewRepository) {
        this.singleMenuItemViewRepository = singleMenuItemViewRepository;
        this.menuItemTypeViewRepository = menuItemTypeViewRepository;
        this.dishTypeViewRepository = dishTypeViewRepository;
        this.tasteViewRepository = tasteViewRepository;
    }

    @EventHandler
    public void on(SingleMenuItemCreatedEvent event, @Timestamp java.time.Instant createdTime) throws Exception {
        MenuItemTypeView menuItemTypeView = this.menuItemTypeViewRepository.findById(event.getMenuItemTypeId().getIdentifier())
                .orElseThrow(() -> new BizException(BizErrorCode.MENU_ITEM_TYPE_NOT_EXISTED));
        DishTypeView dishTypeView = this.dishTypeViewRepository.findById(event.getDishTypeId().getIdentifier())
                .orElseThrow(() -> new BizException(BizErrorCode.DISH_TYPE_NOT_EXISTED));

        SingleMenuItemView singleMenuItemView = new SingleMenuItemView(
                event.getId().getIdentifier(),
                event.getRestaurantId().getIdentifier(),
                event.getName(),
                event.getMenuItemTypeId().getIdentifier(),
                menuItemTypeView.getName(),
                event.getDishTypeId().getIdentifier(),
                dishTypeView.getName(),
                event.getPrice(),
                false,
                new Date(createdTime.toEpochMilli())
        );

        if (event.getTasteId() != null) {
            TasteView tasteView = this.tasteViewRepository.findById(event.getTasteId().getIdentifier())
                    .orElseThrow(() -> new BizException(BizErrorCode.TASTE_NOT_EXISTED));

            singleMenuItemView.setTasteId(event.getTasteId().getIdentifier());
            singleMenuItemView.setTasteName(tasteView.getName());
        }

        this.singleMenuItemViewRepository.save(singleMenuItemView);
    }

    @EventHandler
    public void on(SingleMenuItemUpdatedEvent event, @Timestamp java.time.Instant updatedTime) throws Exception {
        SingleMenuItemView singleMenuItemView = this.singleMenuItemViewRepository.findById(event.getId().getIdentifier())
                .orElseThrow(() -> new BizException(BizErrorCode.MENU_ITEM_NOT_EXISTED));

        if (event.getDishTypeId() != null) {
            singleMenuItemView.setDishTypeId(event.getDishTypeId().getIdentifier());
            DishTypeView dishTypeView = this.dishTypeViewRepository.findById(event.getDishTypeId().getIdentifier())
                    .orElseThrow(() -> new BizException(BizErrorCode.DISH_TYPE_NOT_EXISTED));
            singleMenuItemView.setDishTypeName(dishTypeView.getName());
        }

        if (event.getName() != null) {
            singleMenuItemView.setName(event.getName());
        }

        if (event.getTasteId() != null) {
            singleMenuItemView.setTasteId(event.getTasteId().getIdentifier());
            TasteView tasteView = this.tasteViewRepository.findById(event.getTasteId().getIdentifier())
                    .orElseThrow(() -> new BizException(BizErrorCode.TASTE_NOT_EXISTED));
            singleMenuItemView.setMenuItemTypeName(tasteView.getName());
        }

        if (event.getPrice() != null) {
            singleMenuItemView.setPrice(event.getPrice());
        }

        if (event.getOnShelve() != null) {
            singleMenuItemView.setOnShelve(event.getOnShelve());
        }

        singleMenuItemView.setUpdatedAt(new Date(updatedTime.toEpochMilli()));

        this.singleMenuItemViewRepository.save(singleMenuItemView);
    }

    @EventHandler
    public void on(SingleMenuItemDeletedEvent event) {
        this.singleMenuItemViewRepository.findById(event.getMenuItemId().getIdentifier())
                .ifPresent(singleMenuItemView -> this.singleMenuItemViewRepository.delete(singleMenuItemView));
    }
}
