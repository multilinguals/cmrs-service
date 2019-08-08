package org.multilinguals.enterprise.cmrs.interfaces.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class CreateRestaurantDTO {
    @NotEmpty(message = "{restaurant.name.NotEmpty}")
    @Length(max = 20, message = "{restaurant.name.length}")
    private String name;

    @Length(min = 5, max = 200, message = "{restaurant.description.length}")
    private String description;

    public CreateRestaurantDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
