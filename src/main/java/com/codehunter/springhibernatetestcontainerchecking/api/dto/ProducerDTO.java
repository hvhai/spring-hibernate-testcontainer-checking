package com.codehunter.springhibernatetestcontainerchecking.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerDTO {
    private Long id;
    private String name;
    private LocalDate birthdate;
}
