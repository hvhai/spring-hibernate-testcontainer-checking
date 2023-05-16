package com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Producer")
@Table(name = "producer")
@Setter
@Getter
public class ProducerDAO {
    @Id
    @Tsid
    private Long id;
    private String name;
}
