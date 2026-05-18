package com.automaster.controller;
import com.automaster.dto.GarantiaRequestDTO;
import com.automaster.dto.GarantiaResponseDTO;
import com.automaster.service.GarantiaServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/garantias")
public class GarantiaController {

    @Autowired
    private GarantiaServiceImpl garantiaService;

    @PostMapping("/activar")
    public ResponseEntity<GarantiaResponseDTO> activarGarantia(@Valid @RequestBody GarantiaRequestDTO request) {
        log.info("Petición POST recibida para activar nueva garantía");
        GarantiaResponseDTO response = garantiaService.activarGarantia(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}