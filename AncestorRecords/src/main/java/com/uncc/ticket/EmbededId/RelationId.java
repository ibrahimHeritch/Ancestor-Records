package com.uncc.ticket.EmbededId;

import com.uncc.ticket.model.PersonEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelationId implements Serializable {
    @Column(name = "person1_id")
    private Long person1Id;

    @Column(name = "person2_id")
    private Long person2Id;

    public RelationId() {
    }

    public RelationId(PersonEntity person1, PersonEntity person2) {
        if(person1.getId()<person2.getId()){ // this assures the Ids (a,b) and (b,a) are considered the same
            this.person1Id = person1.getId();
            this.person2Id = person2.getId();
        }else{
            this.person2Id = person1.getId();
            this.person1Id = person2.getId();
        }

    }

    public Long getPerson1Id() {
        return person1Id;
    }

    public void setPerson1Id(Long person1Id) {
        this.person1Id = person1Id;
    }

    public Long getPerson2Id() {
        return person2Id;
    }

    public void setPerson2Id(Long person2Id) {
        this.person2Id = person2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationId that = (RelationId) o;
        return Objects.equals(person1Id, that.person1Id) &&
                Objects.equals(person2Id, that.person2Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person1Id, person2Id);
    }
}