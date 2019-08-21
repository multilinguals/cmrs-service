package org.multilinguals.enterprise.cmrs.interfaces.dto.command.mrgroup;

import org.hibernate.validator.constraints.Length;

public class UpdateMealReservationGroupDetailsDTO {
    @Length(min = 4, max = 8)
    private String name;

    @Length(min = 5, max = 200)
    private String description;

    public UpdateMealReservationGroupDetailsDTO() {
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
