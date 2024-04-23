package com.scotiabank.pe.retoJMT.service.impl;

import com.scotiabank.pe.retoJMT.constants.AlumnConstants;
import com.scotiabank.pe.retoJMT.dto.AlumnDto;
import com.scotiabank.pe.retoJMT.enums.StatusEnum;
import com.scotiabank.pe.retoJMT.mapper.AlumnMapper;
import com.scotiabank.pe.retoJMT.repository.AlumnDbcRepository;
import com.scotiabank.pe.retoJMT.service.AlumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnServiceImpl implements AlumnService {
    private static final Logger log = LoggerFactory.getLogger(AlumnServiceImpl.class);
    @Autowired
    private AlumnDbcRepository alumnDbcRepository;
    @Autowired
    private AlumnMapper alumnMapper;

    @Override
    public Mono<Void> saveAlumn(AlumnDto alumnDto) {
        log.info(AlumnConstants.MESSAGE_INIT_SAVED, alumnDto);
        return alumnDbcRepository.findById(alumnDto.getId())
                .flatMap(exists -> Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, AlumnConstants.MESSAGE_ALUMN_ALREADY_EXISTS_ERROR))
                ).switchIfEmpty(alumnDbcRepository.save(alumnMapper.mapToAlumn(alumnDto))).then();
    }

    @Override
    public Flux<AlumnDto> getActiveAlumns() {
        log.info(AlumnConstants.MESSAGE_INIT_ACTIVE_ALUMNS);
        return alumnDbcRepository.findAlumnByState(StatusEnum.activo.name())
                .map(alumnMapper::mapToAlumnDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, AlumnConstants.MESSAGE_ALUMN_NO_ACTIVE_ALUMNS)));
    }

}
