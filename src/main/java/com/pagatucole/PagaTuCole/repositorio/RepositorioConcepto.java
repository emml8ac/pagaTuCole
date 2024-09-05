package com.pagatucole.PagaTuCole.repositorio;

import com.pagatucole.PagaTuCole.modelo.Concepto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioConcepto extends JpaRepository<Concepto,Long> {
}
