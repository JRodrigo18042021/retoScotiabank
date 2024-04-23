package com.scotiabank.pe.retoJMT.controller;

import com.scotiabank.pe.retoJMT.constants.AlumnConstants;
import com.scotiabank.pe.retoJMT.dto.AlumnDto;
import com.scotiabank.pe.retoJMT.service.AlumnService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(AlumnConstants.API_REQUEST)
@Validated
public class AlumnController {
    @Autowired
    private AlumnService service;

    @PostMapping
    @Operation(summary = "registrar alumnos", description = "Endpoint para registrar  alumnos ")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> saveAlumn(@Validated @RequestBody AlumnDto alumnDto) {
        return service.saveAlumn(alumnDto);
    }

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "alumnos activos", description = "Endpoint para listar solo alumnos activos")
    public ResponseEntity<Flux<AlumnDto>> getActiveAlumns() {
        var alumnDtoFlux = service.getActiveAlumns();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(alumnDtoFlux);
    }

}
