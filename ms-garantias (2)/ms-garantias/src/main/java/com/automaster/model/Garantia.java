package com.automaster.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "garantias")
@Data
public class Garantia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12)
    private String rutCliente;

    @Column(nullable = false)
    private Long idVehiculo; // Usamos ID para coincidir con tu MS-Stock

    @Column(nullable = false)
    private LocalDate fechaActivacion;

    @Column(nullable = false)
    private Integer mesesCobertura;

    @Column(nullable = false, length = 20)
    private String estado; // EJ: "ACTIVA", "VENCIDA", "ANULADA"

    @Column(nullable = false)
    private String terminos;
}