package com.codehunter.springhibernatetestcontainerchecking.core.repository;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;

import java.util.List;

public interface ProducerRepository {
    Producer save(Producer producer);

    List<Producer> getAll();

    void delete(Long id);

    public void findAndUpdateProducer(Long id);
}
