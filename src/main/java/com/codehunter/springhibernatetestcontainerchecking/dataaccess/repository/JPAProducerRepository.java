package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;

import com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity.ProducerDAO;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPAProducerRepository extends JpaRepository<ProducerDAO, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<ProducerDAO> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select p from Producer p")
    List<ProducerDAO> getListProducer();
}
