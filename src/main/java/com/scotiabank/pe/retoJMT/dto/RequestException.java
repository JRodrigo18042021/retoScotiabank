package com.scotiabank.pe.retoJMT.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@AllArgsConstructor
@Getter
public class RequestException {
    private int code;
    private String error;
    private List<String> description;
}
