package com.pagatucole.PagaTuCole.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Entity
@Table(name = "tconcepto_mes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConceptoMes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_concepto", referencedColumnName = "id")
    private Concepto concepto;

    @ManyToOne
    @JoinColumn(name = "id_mes", referencedColumnName = "id")
    private Mes mes;
}
