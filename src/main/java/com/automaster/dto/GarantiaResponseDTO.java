package com.automaster.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GarantiaResponseDTO {
    private Long id;
    private String rutCliente;
    private Long idVehiculo;
    private LocalDate fechaActivacion;
    private LocalDate fechaVencimiento; // Calculada automáticamente
    private Integer mesesCobertura;
    private String estado;
    private String terminos;
}