package com.codehunter.springhibernatetestcontainerchecking;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import com.codehunter.springhibernatetestcontainerchecking.core.usecase.ProducerUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class SpringHibernateTestcontainerCheckingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHibernateTestcontainerCheckingApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(ProducerUseCase producerUseCase, ProducerRepository producerRepository) {
        return args -> {
//            Producer apple = producerUseCase.create(Producer.builder().name("Apple").build());
//            log.info("Create producer: {}", apple);
//
//            log.info("getAll start" );
//            producerRepository.getAll().forEach(System.out::println);
//            log.info("getAll end" );
        };
    }

}
