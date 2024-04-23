package com.scotiabank.pe.retoJMT.config;

import com.scotiabank.pe.retoJMT.mapper.AlumnMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public AlumnMapper alumnMapper() {
        return AlumnMapper.INSTANCE;
    }
}
