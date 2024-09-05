package com.pagatucole.PagaTuCole.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tCronogramaPagos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cronograma{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "dni")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_pago", referencedColumnName = "id")
    private Pago pago;

    @ManyToOne
    @JoinColumn(name = "id_concepto_mes", referencedColumnName = "id")
    private ConceptoMes conceptoMes;

    private String estado;
}
