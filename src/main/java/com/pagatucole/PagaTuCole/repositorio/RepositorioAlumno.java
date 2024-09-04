package com.pagatucole.PagaTuCole.repositorio;

import com.pagatucole.PagaTuCole.modelo.Alumno;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioAlumno extends JpaRepository<Alumno, String> {
    List<Alumno> findByNivelAndGradoAndSeccion(String nivel, String grado, String seccion);
    Alumno findByDni(String dni);


}
