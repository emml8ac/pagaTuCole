package com.pagatucole.PagaTuCole.controlador;

import com.pagatucole.PagaTuCole.modelo.Alumno;
import com.pagatucole.PagaTuCole.modelo.Persona;
import com.pagatucole.PagaTuCole.servicios.ServicioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gestion-alumnos")
public class AlumnoController {
    @Autowired
    private ServicioAlumno servicioAlumno;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String paginaGestion(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("persona", new Persona());
        return "gestional";
    }
    @PostMapping("/listar")
    public String listar(@RequestParam("nivel") String nivel,
                         @RequestParam("grado") String grado,
                         @RequestParam("seccion") String seccion,
                         Model model){
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("persona", new Persona());
        List<Alumno> alumnos = servicioAlumno.filtrarAlumnos(nivel, grado, seccion);
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("nivelSeleccionado", nivel);
        model.addAttribute("gradoSeleccionado", grado);
        model.addAttribute("seccionSeleccionado", seccion);
        return "gestional";
    }

    @PostMapping("/save")
    public String saveAlumno(@ModelAttribute Alumno alumno, @ModelAttribute Persona persona) {
        servicioAlumno.saveAlumnoAndPersona(persona, alumno);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }
}
