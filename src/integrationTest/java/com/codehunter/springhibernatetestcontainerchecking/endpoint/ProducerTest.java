package com.codehunter.springhibernatetestcontainerchecking.endpoint;

import com.codehunter.springhibernatetestcontainerchecking.TestContainerBase;
import com.codehunter.springhibernatetestcontainerchecking.api.dto.ProducerDTO;
import com.codehunter.springhibernatetestcontainerchecking.config.TestClientConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {TestClientConfig.class})
@RequiredArgsConstructor
public class ProducerTest extends TestContainerBase {

    @Autowired
    private TestRestTemplate restTemplate;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ProducerCrudTest {
        private ProducerDTO currentProducer;

        @Test
        @Order(1)
        @DisplayName("Given a producer with name when create producer then return new producer")
        public void createProducer() {
            var id = "integration test name";
            var body = ProducerDTO.builder().name(id).build();
            var response = restTemplate.exchange("/producers",
                    HttpMethod.POST,
                    new HttpEntity<>(body),
                    ProducerDTO.class);

            assertThat(response).isNotNull();

            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getName()).isEqualTo(id);
            currentProducer = response.getBody();

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }

        @Test
        @Order(2)
        @DisplayName("Given producer already exist in DB when list all producer then return producer list")
        public void listAllProducer() {
            var response = restTemplate.exchange("/producers",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProducerDTO>>() {
                    });

            assertThat(response).isNotNull();

            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().size()).isEqualTo(1);
            assertThat(response.getBody().get(0)).isEqualTo(currentProducer);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        @Order(3)
        @DisplayName("Given producer already exist in DB when delete producer then action is success")
        public void deleteProducer() {
            var response = restTemplate.exchange(String.format("/producers/%s", currentProducer.getId()),
                    HttpMethod.DELETE, null, Void.class);

            assertThat(response).isNotNull();
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }

}
