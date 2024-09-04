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
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getDni() {
//        return dni;
//    }
//
//    public void setDni(String dni) {
//        this.dni = dni;
//    }
//
//    public String getSeccion() {
//        return seccion;
//    }
//
//    public void setSeccion(String seccion) {
//        this.seccion = seccion;
//    }
//
//    public String getNivel() {
//        return nivel;
//    }
//
//    public void setNivel(String nivel) {
//        this.nivel = nivel;
//    }
//
//    public String getGrado() {
//        return grado;
//    }
//
//    public void setGrado(String grado) {
//        this.grado = grado;
//    }
//
//    public Persona getPersona() {
//        return persona;
//    }
//
//    public void setPersona(Persona persona) {
//        this.persona = persona;
//    }
//
//

}
