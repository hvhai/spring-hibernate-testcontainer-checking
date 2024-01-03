package com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "GUESTS")
@PrimaryKeyJoinColumn(name = "PERSION_ID")
public class Guest extends Person {
    @Column(name = "TEMPORARY_NAME")
    private String tempName;
}
