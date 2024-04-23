package com.scotiabank.pe.retoJMT;

import com.scotiabank.pe.retoJMT.dto.AlumnDto;
import com.scotiabank.pe.retoJMT.entity.Alumn;

public class AlumnConvertTest {
    public static Alumn alumnTest(Integer id){
        return Alumn.builder()
                .id(id)
                .name("Armando")
                .lastName("Melendez")
                .state("activo")
                .age(20).build();
    }
    public static AlumnDto alumnDtoTest(Integer id){
        return AlumnDto.builder()
                .id(id)
                .name("Armando")
                .lastName("Melendez")
                .state("activo")
                .age(20).build();
    }
    public static AlumnDto alumnDtoTestEmpty(){
        return AlumnDto.builder()
                .build();
    }
}
