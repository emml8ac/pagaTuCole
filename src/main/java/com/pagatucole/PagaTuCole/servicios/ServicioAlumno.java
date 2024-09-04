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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void update( Alumno alumno) throws Exception {
        Persona persona = repositorioPersona.findById(alumno.getDni()).orElseThrow(()->new Exception("La persona no existe"));
        persona.setApMaterno(alumno.getPersona().getApMaterno());
        persona.setApPaterno(alumno.getPersona().getApPaterno());
        persona.setDireccion(alumno.getPersona().getDireccion());
        persona.setTelefono(alumno.getPersona().getTelefono());
        persona.setEmail(alumno.getPersona().getEmail());
        repositorioPersona.save(persona);
        Alumno alumnoExistente = repositorioAlumno.findById(alumno.getDni())
                .orElseThrow(() -> new Exception("El alumno no existe"));
        alumnoExistente.setSeccion(alumno.getSeccion());
        alumnoExistente.setNivel(alumno.getNivel());
        alumnoExistente.setGrado(alumno.getGrado());
        repositorioAlumno.save(alumnoExistente);


    }
    @Transactional
    public void delete(String dni) throws Exception {
        // Primero, eliminamos la relación con la Persona
        Alumno alumnoExistente = repositorioAlumno.findById(dni)
                .orElseThrow(() -> new Exception("El alumno no existe"));
        Persona personaExistente = repositorioPersona.findById(dni)
                .orElseThrow(() -> new Exception("La persona no existe"));
        Usuario usuarioExistente = repositorioUsuario.findByuserid(dni);
        // Finalmente, eliminamos la entidad Usuario
        repositorioUsuario.delete(usuarioExistente);
        // Luego, eliminamos la entidad Alumno
        repositorioAlumno.delete(alumnoExistente);
        // Eliminar la entidad Persona
        repositorioPersona.delete(personaExistente);

    }

    public Alumno findbydni(String dni){
        return repositorioAlumno.findByDni(dni);
    }
}
