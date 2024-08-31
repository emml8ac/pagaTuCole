package com.pagatucole.PagaTuCole.servicios;
import com.pagatucole.PagaTuCole.modelo.Alumno;
import com.pagatucole.PagaTuCole.modelo.Persona;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import com.pagatucole.PagaTuCole.repositorio.RepositorioAlumno;
import com.pagatucole.PagaTuCole.repositorio.RepositorioPersona;
import com.pagatucole.PagaTuCole.repositorio.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioAlumno {
    @Autowired
    private RepositorioPersona repositorioPersona;

    @Autowired
    private RepositorioAlumno repositorioAlumno;
    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ServicioUsuario servicioUsuario;
    public void saveAlumnoAndPersona(Persona persona, Alumno alumno) {
        if (persona.getDni() == null || persona.getDni().isEmpty()) {
            throw new IllegalArgumentException("DNI no puede ser nulo o vacío");
        }
        // Primero, guarda la persona
        if (!repositorioPersona.existsById(persona.getDni())) {
            repositorioPersona.saveAndFlush(persona);
        }
        // Luego, guarda el alumno con el 'dni asociado
        alumno.setDni(persona.getDni());
        repositorioAlumno.save(alumno);
        String busq =  persona.getNombre().charAt(0)+""+persona.getApPaterno()+persona.getApMaterno().charAt(0);
        servicioUsuario.actualizarPasswordPorDni(busq);
    }
    public List<Alumno> listar(){
        return repositorioAlumno.findAll();
    }
    public List<Alumno> filtrarAlumnos(String nivel, String grado, String seccion) {
        // Lógica para filtrar alumnos basado en los parámetros proporcionados
        return repositorioAlumno.findByNivelAndGradoAndSeccion(nivel, grado, seccion);
    }
}
