package org.multilinguals.enterprise.cmrs.query.menuitemtype;

import org.multilinguals.enterprise.cmrs.infrastructure.i18n.I18Translator;
import org.multilinguals.enterprise.cmrs.infrastructure.persistence.Localizable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;

public class MenuItemTypeView implements Localizable {
    @Id
    private String id;

    private String name;

    @Transient
    private String localName;

    private Date createdAt;

    private Date updatedAt;

    public MenuItemTypeView(String id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    @Override
    public void localize(I18Translator i18Translator) {
        this.localName = i18Translator.localize("MENU_ITEM_TYPE_" + this.getName());
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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
