package com.scotiabank.pe.retoJMT.exception;

import com.scotiabank.pe.retoJMT.dto.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalAdviceAlumn {
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus
    public ResponseEntity<RequestException> webExchangeBindExceptions(WebExchangeBindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        HttpStatus status = ex.getStatus();
        List<String> cadenaErrors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        RequestException resultaRequestException = RequestException.builder()
                .code(status.value())
                .error(status.getReasonPhrase())
                .description(cadenaErrors)
                .build();
        return new ResponseEntity<>(resultaRequestException, status);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus
    public ResponseEntity<RequestException> responseStatusExceptions(ResponseStatusException ex) {
        HttpStatus status = ex.getStatus();
        RequestException resultaRequestException = RequestException.builder()
                .code(status.value())
                .error(status.getReasonPhrase())
                .description(Collections.singletonList(ex.getReason()))
                .build();
        return new ResponseEntity<>(resultaRequestException, status);
    }
}
