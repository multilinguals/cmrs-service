package org.multilinguals.enterprise.cmrs.interfaces.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class UpdateSingleMenuItemDTO {
    @Length(max = 10)
    private String name;

    private String dishTypeId;

    private String tasteId;

    @Min(value = 0)
    private BigDecimal price;

    private Boolean onShelve;

    public UpdateSingleMenuItemDTO() {
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

    public Boolean getOnShelve() {
        return onShelve;
    }

    public void setOnShelve(Boolean onShelve) {
        this.onShelve = onShelve;
    }
}
