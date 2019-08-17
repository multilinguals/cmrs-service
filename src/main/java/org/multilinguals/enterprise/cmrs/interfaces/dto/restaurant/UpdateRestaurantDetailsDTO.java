package org.multilinguals.enterprise.cmrs.interfaces.dto.restaurant;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class UpdateRestaurantDetailsDTO {
    @NotEmpty()
    @Length(max = 20)
    private String name;

    @Length(min = 5, max = 200)
    private String description;

    public UpdateRestaurantDetailsDTO() {
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
