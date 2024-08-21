package com.pagatucole.PagaTuCole.controlador;

import com.pagatucole.PagaTuCole.modelo.Alumno;
import com.pagatucole.PagaTuCole.modelo.Persona;
import com.pagatucole.PagaTuCole.servicios.ServicioAlumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class AlumnoController {
    @Autowired
    private ServicioAlumno servicioAlumno;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/gestion-alumnos")
    public String showForm(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("persona", new Persona());
        return "gestion-alumnos";
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
