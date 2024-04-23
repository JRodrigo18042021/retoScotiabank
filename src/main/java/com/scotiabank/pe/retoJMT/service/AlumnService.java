package com.scotiabank.pe.retoJMT.service;

import com.scotiabank.pe.retoJMT.dto.AlumnDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnService {
    Mono<Void> saveAlumn(AlumnDto alumnDto);

    Flux<AlumnDto> getActiveAlumns();

}
