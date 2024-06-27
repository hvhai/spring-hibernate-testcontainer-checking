package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;

import com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity.ProducerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAProducerRepository extends JpaRepository<ProducerDAO, Long> {

    @Query("select p from Producer p")
    List<ProducerDAO> getListProducer();


}
