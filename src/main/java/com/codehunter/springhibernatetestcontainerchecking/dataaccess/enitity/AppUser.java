package com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
@PrimaryKeyJoinColumn(name = "PERSON_ID")
public class AppUser extends Person {
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "USER_NAME")
    private String firstName;
    @Column(name = "USER_SURENAME")
    private String lastName;
}