package org.multilinguals.enterprise.cmrs.interfaces.dto.query;

public class FoundUserDTO {
    private String id;

    private String realName;

    public FoundUserDTO(String id, String realName) {
        this.id = id;
        this.realName = realName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
