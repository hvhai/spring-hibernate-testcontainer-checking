package com.codehunter.springhibernatetestcontainerchecking.core.usecase;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@Slf4j
public class HikariUseCase {

    private final ProducerRepository producerRepository;


    @Transactional
    public void exhaustHikari() {
        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Task " + taskNumber + " executed by thread: " + Thread.currentThread().getName());
                producerRepository.save(Producer.builder().name("hikari" + taskNumber).build());
                producerRepository.getAll();
            });
        }
        executor.shutdown();
    }
}
