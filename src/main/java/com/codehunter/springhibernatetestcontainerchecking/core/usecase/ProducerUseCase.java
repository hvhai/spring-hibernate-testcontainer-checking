package com.codehunter.springhibernatetestcontainerchecking.core.usecase;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
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
}
