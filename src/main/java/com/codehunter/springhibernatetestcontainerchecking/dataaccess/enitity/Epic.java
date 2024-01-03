package com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "EPICS")
public class Epic extends Task{
    @Column(name = "PROJECT_ID")
    private String projectId;
    @Column(name = "PRODUCT_OWNER_NAME")
    private String productOwner;
}
