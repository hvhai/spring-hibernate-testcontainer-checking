package com.codehunter.springhibernatetestcontainerchecking;

import com.codehunter.springhibernatetestcontainerchecking.config.ContainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestMyApplication {
    public static void main(String[] args) {
        SpringApplication.from(Application::main)
                .with(ContainersConfiguration.class)
                .run(args);
    }
}
