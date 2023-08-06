package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;


import com.codehunter.springhibernatetestcontainerchecking.TestContainerBase;
import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ProducerRepositoryImplTest extends TestContainerBase {

    private final ProducerRepository producerRepository;

    public ProducerRepositoryImplTest(@Autowired ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Test
    void createProducer() {
        Producer actual = producerRepository.save(
                Producer.builder().name("test container").build());
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo("test container");
    }

    @Test
    void getAllProducer() {
        // given
        producerRepository.save(
                Producer.builder().name("test container").build());

        // when
        List<Producer> actual = producerRepository.getAll();

        // then
        assertThat(actual).isNotNull().hasSize(1);
        assertThat(actual.get(0).getId()).isNotNull();
        assertThat(actual.get(0).getName()).isEqualTo("test container");
    }
}
