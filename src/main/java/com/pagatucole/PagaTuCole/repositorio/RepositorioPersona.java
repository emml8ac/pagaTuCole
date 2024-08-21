package com.pagatucole.PagaTuCole.repositorio;

import com.pagatucole.PagaTuCole.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPersona extends JpaRepository<Persona, String> {

}
