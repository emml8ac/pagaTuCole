package com.pagatucole.PagaTuCole.repositorio;

import com.pagatucole.PagaTuCole.modelo.ConceptoMes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioConceptoMes extends JpaRepository<ConceptoMes,Long> {

    ConceptoMes findByConceptoIdAndMesId(Long conceptoId, Long mesId);
}
