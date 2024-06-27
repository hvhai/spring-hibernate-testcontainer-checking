package com.codehunter.springhibernatetestcontainerchecking;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.usecase.ProducerUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDate;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner runner(@Autowired ProducerUseCase useCase) {
        return args -> {
            useCase.createProducer(Producer.builder().name("Hai").birthdate(LocalDate.of(1991, 12,22)).build());
            useCase.createProducer(Producer.builder().name("Hung").birthdate(LocalDate.of(1995, 7,8)).build());
            useCase.createProducer(Producer.builder().name("Khanh").birthdate(LocalDate.of(2000, 3,18)).build());
        };
    }
}
