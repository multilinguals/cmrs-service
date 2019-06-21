package org.multilinguals.enterprise.cmrs.dto.user.authorization;

public class AggregateCreatedDTO<IDType> {
    private IDType id;

    public AggregateCreatedDTO(IDType id) {
        this.id = id;
    }

    public IDType getId() {
        return id;
    }
}