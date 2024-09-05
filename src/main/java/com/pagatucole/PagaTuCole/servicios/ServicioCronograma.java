package com.pagatucole.PagaTuCole.servicios;

import com.pagatucole.PagaTuCole.modelo.Alumno;
import com.pagatucole.PagaTuCole.modelo.ConceptoMes;
import com.pagatucole.PagaTuCole.modelo.Cronograma;
import com.pagatucole.PagaTuCole.repositorio.RepositorioAlumno;
import com.pagatucole.PagaTuCole.repositorio.RepositorioConceptoMes;
import com.pagatucole.PagaTuCole.repositorio.RepositorioCronogramaPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCronograma {
    @Autowired
    private RepositorioCronogramaPago cronogramaPagoRepository;

    @Autowired
    private RepositorioAlumno alumnoRepository;

    @Autowired
    private RepositorioConceptoMes conceptoMesRepository;

    public Cronograma crearCronograma(String alumnoId, Long conceptoMesId, String estado) {
        Cronograma cronogramaPago = new Cronograma();
        Alumno alumno = alumnoRepository.findByDni(alumnoId);
        ConceptoMes conceptoMes = conceptoMesRepository.findById(conceptoMesId).orElseThrow(() -> new IllegalArgumentException("ConceptoMes no encontrado"));
        cronogramaPago.setAlumno(alumno);
        cronogramaPago.setConceptoMes(conceptoMes);
        cronogramaPago.setEstado(estado);
        return cronogramaPagoRepository.save(cronogramaPago);
    }

    public List<Cronograma> obtenerTodosCronogramas() {
        return cronogramaPagoRepository.findAll();
    }
}
