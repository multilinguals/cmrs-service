package org.multilinguals.enterprise.cmrs.interfaces.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateSingleMenuItemDTO {
    @NotEmpty
    private String restaurantId;

    @NotEmpty
    @Max(value = 5)
    private String name;

    @NotEmpty
    private String dishTypeId;

    private String tasteId;

    @NotNull
    private BigDecimal price;

    public CreateSingleMenuItemDTO() {
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDishTypeId() {
        return dishTypeId;
    }

    public void setDishTypeId(String dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public String getTasteId() {
        return tasteId;
    }

    public void setTasteId(String tasteId) {
        this.tasteId = tasteId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
