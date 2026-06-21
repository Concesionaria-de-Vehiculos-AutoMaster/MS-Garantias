package com.automaster.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GarantiaRequestDTO {

    @NotBlank(message = "El RUT del cliente es obligatorio")
    private String rutCliente;

    @NotNull(message = "El ID del vehículo es obligatorio")
    private Long idVehiculo;

    @NotNull(message = "Los meses de cobertura son obligatorios")
    @Min(value = 6, message = "La garantía mínima es de 6 meses")
    private Integer mesesCobertura;

    @NotBlank(message = "Los términos de la garantía son obligatorios")
    private String terminos;
}