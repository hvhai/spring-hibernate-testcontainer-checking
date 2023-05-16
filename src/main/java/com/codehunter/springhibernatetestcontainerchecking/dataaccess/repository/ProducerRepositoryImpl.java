package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity.ProducerDAO;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.mapper.ProducerDataAccessMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerRepositoryImpl implements ProducerRepository {
    private final SessionFactory sessionFactory;
    private final ProducerDataAccessMapper producerMapper;


    @Override
    public Producer save(Producer producer) {
        var em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        ProducerDAO dao = producerMapper.toProducerDAO(producer);
        em.persist(dao);
        em.flush();
        em.getTransaction().commit();
        em.close();
        return producerMapper.toProducerFromDao(dao);
    }
}
