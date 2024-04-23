package com.scotiabank.pe.retoJMT.repository;

import com.scotiabank.pe.retoJMT.entity.Alumn;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AlumnDbcRepository extends R2dbcRepository<Alumn, Integer> {
    Flux<Alumn> findAlumnByState(String state);
//    @Query("SELECT * from alumn where state=:state")
//    Flux<Alumn> findAlumnByStates(String state);
}
