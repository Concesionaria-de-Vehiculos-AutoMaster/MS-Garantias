package com.automaster.config;
import com.automaster.model.Garantia;
import com.automaster.repository.GarantiaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private GarantiaRepository garantiaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (garantiaRepository.count() == 0) {
            log.info("Creando garantía de prueba inicial...");

            Garantia g1 = new Garantia();
            g1.setRutCliente("12.345.678-9"); // Debe existir en MS-Clientes
            g1.setIdVehiculo(1L); // Debe existir en MS-Stock
            g1.setFechaActivacion(LocalDate.now());
            g1.setMesesCobertura(12);
            g1.setEstado("ACTIVA");
            g1.setTerminos("Cubre motor y transmisión por defectos de fábrica.");

            garantiaRepository.save(g1);
            log.info("Garantía inicial creada con éxito.");
        }
    }
}