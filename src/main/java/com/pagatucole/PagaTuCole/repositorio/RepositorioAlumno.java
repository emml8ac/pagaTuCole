package com.pagatucole.PagaTuCole.repositorio;

import com.pagatucole.PagaTuCole.modelo.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAlumno extends JpaRepository<Alumno, String> {

}
