package org.multilinguals.enterprise.cmrs.interfaces.dto.common;

public class AggregateCreatedDTO<IDType> {
    private IDType id;

    public AggregateCreatedDTO(IDType id) {
        this.id = id;
    }

    public IDType getId() {
        return id;
    }
}