package org.multilinguals.enterprise.cmrs.constant.aggregate.mrgroup;

public enum MealReservationActivityStatus {
    ONGOING(1), SETTLED(2), DONE(3), CLOSED(4),CANCEL(5);

    private int value;

    private MealReservationActivityStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
