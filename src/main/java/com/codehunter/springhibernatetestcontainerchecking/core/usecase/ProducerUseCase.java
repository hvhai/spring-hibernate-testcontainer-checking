package com.codehunter.springhibernatetestcontainerchecking.core.usecase;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ProducerUseCase {
    private final ProducerRepository producerRepository;

    public Producer createProducer(Producer producer) {
        log.info("create producer {}", producer);
        return producerRepository.save(producer);
    }

    public List<Producer> getAllProducer() {
        log.info("get all producers");
        return producerRepository.getAll();
    }

    public void deleteProducer(Long id) {
        log.info("delete producer with id: {}", id);
        producerRepository.delete(id);
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    public void findAndUpdateProducer(Long id) {
        log.info("Find and update with id {}", id);
        producerRepository.findAndUpdateProducer(id);
    }
}
