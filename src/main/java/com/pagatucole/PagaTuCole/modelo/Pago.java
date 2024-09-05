package com.pagatucole.PagaTuCole.modelo;


import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tpago")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pago{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaPago;
    private BigDecimal montoTotal;
    private String estado;

    // Getters y Setters
}
