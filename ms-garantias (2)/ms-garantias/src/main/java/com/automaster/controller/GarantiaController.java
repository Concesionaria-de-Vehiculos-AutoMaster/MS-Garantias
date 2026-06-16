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

@Slf4j
@RestController
@RequestMapping("/api/v1/garantias")
@Tag(name="Garantias ", description = "Venta de accesorios y piezas.")
public class GarantiaController {

    @Autowired
    private GarantiaServiceImpl garantiaService;

    @PostMapping("/activar")
    @Operation(summary = "Obtener todos las garantias ", description = "Obtiene una lista de todas las garantias ")
    public ResponseEntity<GarantiaResponseDTO> activarGarantia(@Valid @RequestBody GarantiaRequestDTO request) {
        log.info("Petición POST recibida para activar nueva garantía");
        GarantiaResponseDTO response = garantiaService.activarGarantia(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GarantiaResponseDTO> obtenerGarantiaPorId(@PathVariable Long id) {
        log.info("Petición GET recibida para buscar garantía por ID: {}", id);
        GarantiaResponseDTO response = garantiaService.obtenerPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGarantia(@PathVariable Long id) {
        log.info("Petición DELETE recibida para eliminar garantía ID: {}", id);
        garantiaService.eliminarGarantia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content es el estándar para un Delete exitoso
    }
}