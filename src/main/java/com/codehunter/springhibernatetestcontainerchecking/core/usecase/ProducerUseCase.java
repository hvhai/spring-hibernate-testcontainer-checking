package com.codehunter.springhibernatetestcontainerchecking.core.usecase;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProducerUseCase {
    private final ProducerRepository producerRepository;
    public Producer create(Producer producer) {
        log.info("create producer {}", producer);
        return producerRepository.save(producer);
    }
}
