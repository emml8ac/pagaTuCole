package com.pagatucole.PagaTuCole.modelo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="tpersona")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Persona {
    @Id
    @Column(name="dni")
    private String dni;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apePaterno")
    private String apPaterno;
    @Column(name="apeMaterno")
    private String apMaterno;
    @Column(name="direccion")
    private String direccion;
    @Column(name="email")
    private String email;
    @Column(name="telefono")
    private String telefono;
    public String nombreCom(){
        return this.nombre+' '+this.getApPaterno()+' '+this.getApMaterno();
    }
}
