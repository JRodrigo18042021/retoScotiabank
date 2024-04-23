package com.scotiabank.pe.retoJMT;

import com.scotiabank.pe.retoJMT.constants.AlumnConstants;
import com.scotiabank.pe.retoJMT.dto.AlumnDto;
import com.scotiabank.pe.retoJMT.model.Alumn;
import com.scotiabank.pe.retoJMT.repository.AlumnDbcRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;


@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class RetoJmtControllerApplicationTests {
    @MockBean
    private AlumnDbcRepository alumnDbcRepository;
    @Autowired
    private WebTestClient client;
    @Value("${config.base.endpoint}")
    private String url;

    public enum TestCodesStatusEnum {OK_200, NOT_FOUND_404, BAD_REQUEST_400}

    AlumnDto alumnDto;

    @ParameterizedTest
    @EnumSource(TestCodesStatusEnum.class)
    void listaAlumnosActivos(TestCodesStatusEnum testCodesStatusEnum) {

        switch (testCodesStatusEnum) {
            case OK_200:
                List<Alumn> alumnos = Collections.singletonList(AlumnConvertTest.alumnTest(2));
                Mockito.when(alumnDbcRepository.findAlumnByState(Mockito.any())).thenReturn(Flux.fromIterable(alumnos));
                client.get()
                        .uri(url.concat(AlumnConstants.API_REQUEST).concat("/active"))
                        .accept(MediaType.APPLICATION_JSON)
                        .exchange()
                        .expectStatus().isOk()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON)
                        .expectBodyList(AlumnDto.class);
            case NOT_FOUND_404:
                Mockito.when(alumnDbcRepository.findAlumnByState(Mockito.any())).thenReturn(Flux.empty());
                client.get()
                        .uri(url.concat(AlumnConstants.API_REQUEST).concat("/active"))
                        .exchange()
                        .expectStatus().isNotFound()
                        .expectHeader().contentType(MediaType.APPLICATION_JSON)
                        .expectBodyList(AlumnDto.class);
        }
    }

    @ParameterizedTest
    @EnumSource(TestCodesStatusEnum.class)
    void saveAlumns(TestCodesStatusEnum testCodesStatusEnum) {
        switch (testCodesStatusEnum) {
            case OK_200:
                alumnDto = AlumnConvertTest.alumnDtoTest(3);
                Mockito.when(alumnDbcRepository.findById(Mockito.anyInt())).thenReturn(Mono.empty());
                Mockito.when(alumnDbcRepository.save(Mockito.any())).thenReturn(Mono.empty());
                client.post().uri(url.concat(AlumnConstants.API_REQUEST))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .body(Mono.just(alumnDto), AlumnDto.class)
                        .exchange()
                        .expectStatus().isCreated();

            case BAD_REQUEST_400:
                alumnDto = AlumnConvertTest.alumnDtoTestEmpty();
                client.post().uri(url.concat(AlumnConstants.API_REQUEST))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .body(Mono.just(alumnDto), AlumnDto.class)
                        .exchange()
                        .expectStatus().isBadRequest();

        }
    }
}
