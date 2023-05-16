package com.codehunter.springhibernatetestcontainerchecking.dataaccess.mapper;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity.ProducerDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerDataAccessMapper {
    Producer toProducerFromDao(ProducerDAO producerDAO);
    ProducerDAO toProducerDAO(Producer producer);
}
