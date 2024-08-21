package com.pagatucole.PagaTuCole.servicios;
import com.pagatucole.PagaTuCole.modelo.Alumno;
import com.pagatucole.PagaTuCole.modelo.Persona;
import com.pagatucole.PagaTuCole.repositorio.RepositorioAlumno;
import com.pagatucole.PagaTuCole.repositorio.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioAlumno {
    @Autowired
    private RepositorioPersona repositorioPersona;

    @Autowired
    private RepositorioAlumno repositorioAlumno;

    public void saveAlumnoAndPersona(Persona persona, Alumno alumno) {
        if (persona.getDni() == null || persona.getDni().isEmpty()) {
            throw new IllegalArgumentException("DNI no puede ser nulo o vacío");
        }

        // Primero, guarda la persona
        if (!repositorioPersona.existsById(persona.getDni())) {
            repositorioPersona.save(persona);
        }

        // Luego, guarda el alumno con el `dni` asociado
        alumno.setDni(persona.getDni());
        repositorioAlumno.save(alumno);
    }
}
