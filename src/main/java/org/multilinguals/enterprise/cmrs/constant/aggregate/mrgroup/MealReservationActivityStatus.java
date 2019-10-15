package org.multilinguals.enterprise.cmrs.constant.aggregate.mrgroup;

public enum MealReservationActivityStatus {
    PENDING(1), ONGOING(2), CANCELED(3), CLOSED(4);

    private int value;

    private MealReservationActivityStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
