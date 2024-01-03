package com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSONS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @Column(name = "PERSON_ID")
    private String id;
    @Column(name = "SESSION_ID")
    private String sessionId;
}