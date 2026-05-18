package com.automaster.service;
import com.automaster.dto.GarantiaRequestDTO;
import com.automaster.dto.GarantiaResponseDTO;
import com.automaster.model.Garantia;
import com.automaster.repository.GarantiaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Slf4j
@Service
public class GarantiaServiceImpl {

    @Autowired
    private GarantiaRepository garantiaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public GarantiaResponseDTO activarGarantia(GarantiaRequestDTO request) {
        log.info("Iniciando activación de garantía para el vehículo ID: {}", request.getIdVehiculo());

        // 1. Validar Cliente (Puerto 8083)
        validarCliente(request.getRutCliente());

        // 2. Validar Vehículo (Puerto 8080)
        validarVehiculo(request.getIdVehiculo());

        // 3. Crear y guardar la garantía
        Garantia garantia = new Garantia();
        garantia.setRutCliente(request.getRutCliente());
        garantia.setIdVehiculo(request.getIdVehiculo());
        garantia.setFechaActivacion(LocalDate.now());
        garantia.setMesesCobertura(request.getMesesCobertura());
        garantia.setEstado("ACTIVA");
        garantia.setTerminos(request.getTerminos());

        Garantia guardada = garantiaRepository.save(garantia);
        log.info("Garantía creada exitosamente. ID: {}", guardada.getId());

        return mapearADTO(guardada);
    }

    private void validarCliente(String rut) {
        log.info("Consultando MS-Clientes para el RUT: {}", rut);
        try {
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/api/clientes/rut/" + rut)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("Cliente no encontrado: {}", rut);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente no existe en el sistema.");
        }
    }

    private void validarVehiculo(Long idVehiculo) {
        log.info("Consultando MS-Stock para el Vehículo ID: {}", idVehiculo);
        try {
            webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8080/api/stock/" + idVehiculo)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            log.error("Vehículo ID {} no encontrado", idVehiculo);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El vehículo no existe en el inventario.");
        }
    }

    private GarantiaResponseDTO mapearADTO(Garantia garantia) {
        GarantiaResponseDTO dto = new GarantiaResponseDTO();
        dto.setId(garantia.getId());
        dto.setRutCliente(garantia.getRutCliente());
        dto.setIdVehiculo(garantia.getIdVehiculo());
        dto.setFechaActivacion(garantia.getFechaActivacion());
        // Calculamos la fecha de vencimiento sumando los meses a la fecha de activación
        dto.setFechaVencimiento(garantia.getFechaActivacion().plusMonths(garantia.getMesesCobertura()));
        dto.setMesesCobertura(garantia.getMesesCobertura());
        dto.setEstado(garantia.getEstado());
        dto.setTerminos(garantia.getTerminos());
        return dto;
    }
}
