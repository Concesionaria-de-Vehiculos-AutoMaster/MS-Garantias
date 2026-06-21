package com.automaster.controller;
import com.automaster.dto.GarantiaRequestDTO;
import com.automaster.dto.GarantiaResponseDTO;
import com.automaster.service.GarantiaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/garantias")
@Tag(name = "Garantia", description = "Operaciones relacionadas con las Garantias")
public class GarantiaController {

    @Autowired
    private GarantiaServiceImpl garantiaService;

    @PostMapping("/activar")
    @Operation(summary = "Activar Garantia ", description = "Aqui podras activar tu garantia ")
    public ResponseEntity<GarantiaResponseDTO> activarGarantia(@Valid @RequestBody GarantiaRequestDTO request) {
        log.info("Petición POST recibida para activar nueva garantía");
        GarantiaResponseDTO response = garantiaService.activarGarantia(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener Garantia por ID", description = "Obtiene Informaccion de Garantia mediante un ID")
    public ResponseEntity<GarantiaResponseDTO> obtenerGarantiaPorId(@PathVariable Long id) {
        log.info("Petición GET recibida para buscar garantía por ID: {}", id);
        GarantiaResponseDTO response = garantiaService.obtenerPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Garantia ", description = "Podras Eliminar tu Garantia ")
    public ResponseEntity<Void> eliminarGarantia(@PathVariable Long id) {
        log.info("Petición DELETE recibida para eliminar garantía ID: {}", id);
        garantiaService.eliminarGarantia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    @Operation(summary = "Listar todas las Garantías", description = "Obtiene una lista completa de todas las garantías registradas en el sistema")
    public ResponseEntity<List<GarantiaResponseDTO>> listarGarantias() {
        log.info("Petición GET recibida para listar todas las garantías");
        List<GarantiaResponseDTO> response = garantiaService.listarTodas();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}