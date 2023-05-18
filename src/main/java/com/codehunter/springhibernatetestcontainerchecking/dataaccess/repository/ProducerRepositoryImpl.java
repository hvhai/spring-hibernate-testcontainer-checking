package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity.ProducerDAO;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.mapper.ProducerDataAccessMapper;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProducerRepositoryImpl implements ProducerRepository {
    private final EntityManagerFactory entityManagerFactory;
    private final ProducerDataAccessMapper producerMapper;
    private final JPAProducerRepository jpaProducerRepository;


    @Override
    @Transactional
    public Producer save(Producer producer) {
        ProducerDAO dao = jpaProducerRepository.save(producerMapper.toProducerDAO(producer));
        return producerMapper.toProducerFromDao(dao);
    }

    @Override
    @Transactional
    public List<Producer> getAll() {
//        List<ProducerDAO> result = jpaProducerRepository.findAll();
        List<ProducerDAO> result = jpaProducerRepository.getListProducer();
        return result.stream()
                .map(producerMapper::toProducerFromDao)
                .collect(Collectors.toList());
    }
}
