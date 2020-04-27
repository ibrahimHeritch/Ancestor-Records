package com.uncc.ticket.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;


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

    public PersonEntity() {

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
}
