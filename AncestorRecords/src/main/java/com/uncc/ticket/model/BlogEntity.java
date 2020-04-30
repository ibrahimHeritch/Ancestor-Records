package com.uncc.ticket.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.uncc.ticket.model.UsersEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.StringBufferInputStream;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "blogs")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class BlogEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owners_id",referencedColumnName = "id")
    private UsersEntity owner;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @ManyToMany
    List<PersonEntity> subjects;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public BlogEntity() {
    }

    public List<PersonEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<PersonEntity> subjects) {
        this.subjects = subjects;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOwner(UsersEntity owner) {
        this.owner = owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public UsersEntity getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public String getNamesOfSubjects(){
        String s = "";
        if (this.subjects == null){
            s="Null subjects";
        }else if( this.subjects.isEmpty()){
            s= "No Subjects";
        }else if(this.subjects.size()==1){
            s= this.subjects.get(0).getName();
        }else{
            for(int i=0;i<this.subjects.size()-1;i++){
                s+=this.subjects.get(i).getName();
                s+=", ";
            }
            s+="and ";
            s+=this.subjects.get(this.subjects.size()-1).getName();
        }
        return s;
    }

}
