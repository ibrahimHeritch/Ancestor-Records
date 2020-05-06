package com.uncc.ticket.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.uncc.ticket.EmbededId.RelationId;


import javax.management.relation.Relation;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "persons")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class PersonEntity {

    @Id
    @GeneratedValue
    private Long id;



    private String fname;
    private String mname;

    private String lname;

    private String cityOfBirth;

    private String countryOfBirth;

    private Date DOB;
    @ManyToMany(mappedBy="subjects")
    List<BlogEntity> blogsAboutMe;

    @ManyToMany
    List<PersonEntity> following;

    @ManyToMany
    public List<RelationEntity> myRelations;

    @ManyToMany
    public List<RelationEntity> outRelations;

    public PersonEntity() {

    }

    public List<PersonEntity> getFollowing() {
        return following;
    }

    public void setFollowing(List<PersonEntity> following) {
        this.following = following;
    }

    public void addFollowing(PersonEntity person){
        this.following.add(person);
    }
    public List<BlogEntity> getBlogsAboutMe() {
        return blogsAboutMe;
    }

    public void setBlogsAboutMe(List<BlogEntity> blogsAboutMe) {
        this.blogsAboutMe = blogsAboutMe;
    }
    public String isFollowing(PersonEntity p){

        if(following.contains(p)){
            return "true";
        }else{
            return "false";
        }
    }
    public String isRelation(PersonEntity p){
        for(RelationEntity r:myRelations){
            if(r.getRId().getPerson2Name().equals(p.getName())){
                return "true";
            }
        }
        return "false";

    }
    public Long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getLname() {
        return lname;
    }

    public String getName(){
        return fname+" "+lname;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void addRelation(RelationEntity newRE) {
        if(this.getRelations()==null){
            this.setRelations(new ArrayList<RelationEntity>());
        }
        myRelations.add(newRE);
    }

    public void setRelations(List<RelationEntity> newRE) {this.myRelations = newRE;}

    public List<RelationEntity> getRelations() { return myRelations; }

    public void addOutRelation(RelationEntity newRE) {
        if (this.getOutRelations() == null) {
            this.setOutRelations(new ArrayList<RelationEntity>());
        }
        outRelations.add(newRE);
    }

    public void setOutRelations(List<RelationEntity> newRE) {this.outRelations = newRE;}

    public List<RelationEntity> getOutRelations() { return outRelations; }
}
