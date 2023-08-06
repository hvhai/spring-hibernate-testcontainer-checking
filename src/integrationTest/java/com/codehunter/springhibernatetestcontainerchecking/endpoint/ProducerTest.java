package com.codehunter.springhibernatetestcontainerchecking.endpoint;

import com.codehunter.springhibernatetestcontainerchecking.TestContainerBase;
import com.codehunter.springhibernatetestcontainerchecking.api.dto.ProducerDTO;
import com.codehunter.springhibernatetestcontainerchecking.config.TestClientConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {TestClientConfig.class})
@RequiredArgsConstructor
public class ProducerTest extends TestContainerBase {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createProducer() {
        var body = ProducerDTO.builder().name("integration test name").build();
        var response = restTemplate.exchange("/producers", HttpMethod.POST, new HttpEntity<>(body), ProducerDTO.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}
