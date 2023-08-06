package com.codehunter.springhibernatetestcontainerchecking.api.mapper;

import com.codehunter.springhibernatetestcontainerchecking.api.dto.ProducerDTO;
import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    Producer toProducer(ProducerDTO producerDTO);

    ProducerDTO toProducerDto(Producer producer);

}
