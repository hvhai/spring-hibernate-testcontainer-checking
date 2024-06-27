package com.codehunter.springhibernatetestcontainerchecking.dataaccess.repository;

import com.codehunter.springhibernatetestcontainerchecking.core.domain.Producer;
import com.codehunter.springhibernatetestcontainerchecking.core.repository.ProducerRepository;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.enitity.ProducerDAO;
import com.codehunter.springhibernatetestcontainerchecking.dataaccess.mapper.ProducerDataAccessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProducerRepositoryImpl implements ProducerRepository {
    private final ProducerDataAccessMapper producerMapper;
    private final JPAProducerRepository jpaProducerRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    @Transactional
    public Producer save(Producer producer) {
        ProducerDAO dao = jpaProducerRepository.save(producerMapper.toProducerDAO(producer));
        return producerMapper.toProducerFromDao(dao);
    }

    @Override
    @Transactional
    public List<Producer> getAll() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("findAllProducerBefore");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("selectDate", LocalDate.of(1998, 1, 1));
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
//        List<ProducerDAO> result = jpaProducerRepository.getListProducer();
//        return result.stream()
//                .map(producerMapper::toProducerFromDao)
//                .toList();
        return Collections.EMPTY_LIST;
    }

    @Override
    public void delete(Long id) {
        jpaProducerRepository.deleteById(id);
    }
}
