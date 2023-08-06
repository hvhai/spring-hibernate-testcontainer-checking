package com.codehunter.springhibernatetestcontainerchecking.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.util.DefaultUriBuilderFactory;

@TestConfiguration
@Lazy
@Slf4j
public class TestClientConfig {
    @Bean
    public TestRestTemplate restTemplate(@LocalServerPort int port) {
        log.info("Create basic TestRestTemplate.");
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:" + port));
        return restTemplate;
    }
}
