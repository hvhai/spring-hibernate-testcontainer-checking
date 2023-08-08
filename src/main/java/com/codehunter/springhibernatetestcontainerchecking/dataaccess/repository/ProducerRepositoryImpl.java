package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity.ProducerDAO;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.mapper.ProducerDataAccessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerRepositoryImpl implements ProducerRepository {
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
        List<ProducerDAO> result = jpaProducerRepository.getListProducer();
        return result.stream()
                .map(producerMapper::toProducerFromDao)
                .toList();
    }

    @Override
    public void delete(Long id) {
        jpaProducerRepository.deleteById(id);
    }
}
