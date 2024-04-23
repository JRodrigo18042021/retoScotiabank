package com.scotiabank.pe.retoJMT.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnDto implements Serializable {
    @Min(value = 0, message = "Id tiene que ser mayor que cero")
    private Integer id;
    @NotBlank(message = "Nombre no puede estar en blanco")
    private String name;
    @NotBlank(message = "Apellido no puede estar en blanco")
    private String lastName;
    @NotNull(message = "Estado no debe ser Nulo")
    @Pattern(regexp = "^(activo|inactivo)$", message = "Estado debe ser 'activo' o 'inactivo'")
    private String state;
    @Min(value = 1, message = "Edad tiene que ser mayor que cero")
    private Integer age;
}
