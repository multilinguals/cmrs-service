package org.multilinguals.enterprise.cmrs.interfaces.dto.mrgroup;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class CreateMealReservationGroupDTO {
    @NotEmpty
    @Length(min = 4, max = 8)
    private String name;

    public CreateMealReservationGroupDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
