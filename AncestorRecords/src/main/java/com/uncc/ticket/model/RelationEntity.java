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
    private RelationId Rid;
    @NotBlank
    private String relationType;

    public RelationEntity() {
    }

    public RelationEntity(PersonEntity person1, PersonEntity person2, @NotBlank String relationType) {
        this.Rid = new RelationId(person1,person2);
        this.relationType = relationType;
        person1.addRelation(this);
        person2.addOutRelation(this);
    }

    public RelationId getRId() {
        return Rid;
    }

    public void setRId(RelationId id) {
        System.out.println("CHANGED RID");
        this.Rid = id;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }
}

