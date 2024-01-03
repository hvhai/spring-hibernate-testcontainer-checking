package com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Task {
    @Id
    @Column(name = "TASK_ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AUTHOR")
    private String author;
}
