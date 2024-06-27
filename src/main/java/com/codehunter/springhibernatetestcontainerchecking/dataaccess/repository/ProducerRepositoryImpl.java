package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity.ProducerDAO;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.mapper.ProducerDataAccessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProducerRepositoryImpl implements ProducerRepository {
    private final ProducerDataAccessMapper producerMapper;
    private final JPAProducerRepository jpaProducerRepository;


    @Override
    public Producer save(Producer producer) {
        ProducerDAO dao = jpaProducerRepository.save(producerMapper.toProducerDAO(producer));
        return producerMapper.toProducerFromDao(dao);
    }

    @Override
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

    @Override
    public void findAndUpdateProducer(Long id) {
        Optional<ProducerDAO> producer = jpaProducerRepository.findById(id);
        if (producer.isPresent()) {
            ProducerDAO producerDAO = producer.get();
            producerDAO.setName("updated");
            jpaProducerRepository.save(producerDAO);
        }
    }
}
