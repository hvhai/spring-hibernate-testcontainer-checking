package com.codehunter.springhibernatetestcontainerchecking.api.controller;

import com.codehunter.springhibernatetestcontainerchecking.api.dto.ProducerDTO;
import com.codehunter.springhibernatetestcontainerchecking.api.mapper.ProducerMapper;
import com.codehunter.springhibernatetestcontainerchecking.core.usecase.ProducerUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producers")
@RequiredArgsConstructor
@Log4j2
public class ProducerController {
    private final ProducerUseCase producerUseCase;
    private final ProducerMapper producerMapper;

    @PostMapping
    ResponseEntity<ProducerDTO> createProducer(@RequestBody ProducerDTO producer) {
        log.info("Create new producer: {}", producer);
        var newProducer = producerUseCase.createProducer(producerMapper.toProducer(producer));
        return new ResponseEntity<>(producerMapper.toProducerDto(newProducer), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<ProducerDTO>> getAll() {
        log.info("Retrieve all producer");
        return new ResponseEntity<>(
                producerUseCase.getAllProducer()
                        .stream()
                        .map(producerMapper::toProducerDto)
                        .toList(),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Delete producer with id: {}", id);
        producerUseCase.deleteProducer(id);
        return ResponseEntity.ok().build();
    }
}
