package com.codehunter.springhibernatetestcontainerchecking.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProducerDTO {
    Long id;
    String name;
}
