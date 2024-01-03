package com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "STORIES")
public class Story extends Task{
    @Column(name = "SPRINT_ID")
    private String sprintId;
}
