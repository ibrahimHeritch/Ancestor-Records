package com.uncc.ticket.model;


import com.uncc.ticket.EmbededId.RelationId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "relation")
public class RelationEntity {

    @EmbeddedId
    private RelationId id;
    @NotBlank
    private String relationType;

    public RelationEntity() {
    }

    public RelationEntity(PersonEntity person1, PersonEntity person2, @NotBlank String relationType) {
        this.id = new RelationId(person1,person2);
        this.relationType = relationType;
    }

    public RelationId getId() {
        return id;
    }

    public void setId(RelationId id) {
        this.id = id;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }
}

