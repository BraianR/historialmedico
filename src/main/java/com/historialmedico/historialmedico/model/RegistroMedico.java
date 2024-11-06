package com.historialmedico.historialmedico.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroMedico {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha no puede estar vacía")
    private Date fecha;
    @NotEmpty(message = "El campo diagnostico no puede estar vacío")
    private String diagnostico;
    @NotEmpty(message = "El campo medico no puede estar vacío")
    private String medico;
    @NotEmpty(message = "El campo institucion no puede estar vacío")
    private String institucion;
    @NotNull(message = "El tipo de registro medico no puede estar vacío")
    private TipoRegistroMedico tipoRegistroMedico;
    private String descripcion;
    private String medicacion;


}
