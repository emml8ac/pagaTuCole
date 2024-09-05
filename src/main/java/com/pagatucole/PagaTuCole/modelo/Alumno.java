package com.pagatucole.PagaTuCole.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tAlumno")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alumno {
    @Id
    @Column(name="dni")
    private String dni;
    @Column(name="seccion")
    private String seccion;
    @Column(name="nivel")
    private String nivel;
    @Column(name="grado")
    private String grado;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dni",insertable=false, updatable=false)
    private Persona persona;

}
