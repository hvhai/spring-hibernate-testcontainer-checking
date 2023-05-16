package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;


import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class ProducerRepositoryImplTest {

  private final ProducerRepository producerRepository;

  public ProducerRepositoryImplTest(@Autowired ProducerRepository producerRepository) {
    this.producerRepository = producerRepository;
  }

  @Container
  static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.33");


  @DynamicPropertySource
  static void mySQLProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", mySQLContainer::getUsername);
    registry.add("spring.datasource.password", mySQLContainer::getPassword);
  }

  @Test
  void createProducer() {
    Producer actual = producerRepository.save(
        Producer.builder().name("test container").build());
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isNotNull();
  }

}
