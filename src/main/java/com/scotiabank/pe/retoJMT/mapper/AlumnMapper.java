package com.scotiabank.pe.retoJMT.mapper;

import com.scotiabank.pe.retoJMT.dto.AlumnDto;
import com.scotiabank.pe.retoJMT.model.Alumn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AlumnMapper {
    AlumnMapper INSTANCE = Mappers.getMapper(AlumnMapper.class);

    @Mapping(target = "id", ignore = true)
    Alumn mapToAlumn(AlumnDto dto);

    AlumnDto mapToAlumnDto(Alumn dto);
}
