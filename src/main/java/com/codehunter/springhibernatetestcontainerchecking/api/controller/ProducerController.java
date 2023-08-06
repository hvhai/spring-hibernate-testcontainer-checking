package com.codehunter.springhibernatetestcontainerchecking.api.controller;

import com.codehunter.springhibernatetestcontainerchecking.api.dto.ProducerDTO;
import com.codehunter.springhibernatetestcontainerchecking.core.usecase.ProducerUseCase;
import com.codehunter.springhibernatetestcontainerchecking.api.mapper.ProducerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producers")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerUseCase producerUseCase;
    private final ProducerMapper producerMapper;

    @PostMapping
    ResponseEntity<ProducerDTO> createProducer(@RequestBody ProducerDTO producer) {
        var newProducer = producerUseCase.create(producerMapper.toProducer(producer));
        return new ResponseEntity<>(producerMapper.toProducerDto(newProducer), HttpStatus.CREATED);
    }
}
