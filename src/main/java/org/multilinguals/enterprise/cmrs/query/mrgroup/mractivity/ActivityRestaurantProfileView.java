package org.multilinguals.enterprise.cmrs.query.mrgroup.mractivity;

public class ActivityRestaurantProfileView {
    private String id;

    private String name;

    public ActivityRestaurantProfileView() {
    }

    public ActivityRestaurantProfileView(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
