package com.pagatucole.PagaTuCole.controlador;

import com.pagatucole.PagaTuCole.modelo.Alumno;
import com.pagatucole.PagaTuCole.modelo.Persona;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import com.pagatucole.PagaTuCole.repositorio.RepositorioPersona;
import com.pagatucole.PagaTuCole.servicios.ServicioAlumno;
import com.pagatucole.PagaTuCole.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/gestion-alumnos")
public class AlumnoController {
    @Autowired
    private ServicioAlumno servicioAlumno;
    @Autowired
    private ServicioUsuario usuarioService;
    @Autowired
    private RepositorioPersona repositorioPersona;

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
                         Model model,RedirectAttributes redirectAttributes){
        if (nivel.isEmpty() || grado.isEmpty() || seccion.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Por favor, complete todos los campos de filtro antes de listar los estudiantes.");
            return "redirect:/gestion-alumnos";
        }
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
    public String saveAlumno(@ModelAttribute Alumno alumno, @ModelAttribute Persona persona, RedirectAttributes redirectAttributes) {
        try {
            servicioAlumno.saveAlumnoAndPersona(persona, alumno);
            Usuario usuario = usuarioService.findByDni(alumno.getDni());
            redirectAttributes.addFlashAttribute("successMessage","Alumno agregado correctamente");
            redirectAttributes.addFlashAttribute("usuario", usuario.getUsername());
            redirectAttributes.addFlashAttribute("password",alumno.getDni());
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Error al agregar el alumno.");
        }
        return "redirect:/gestion-alumnos";
    }

    @PostMapping("/update")
    public String updateAlumno(@ModelAttribute("alumno") Alumno alumno, RedirectAttributes redirectAttributes) {
        try {
            servicioAlumno.update(alumno);
            redirectAttributes.addFlashAttribute("successMessage", "Alumno editado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al editar el alumno.");
        }
        return "redirect:/gestion-alumnos";
    }
    @GetMapping("/confirm-delete/{dni}")
    public String showDeleteAlumnoModal(@PathVariable("dni") String dni, Model model) throws Exception {
        Alumno alumno = servicioAlumno.findbydni(dni);
        Persona persona = repositorioPersona.findById(dni).orElseThrow(() -> new Exception("Alumno no encontrado"));
        model.addAttribute("alumno", alumno);
        model.addAttribute("persona", persona);
        model.addAttribute("openDeleteModal", true);
        return "gestional"; // Renderiza la misma vista de gestión de alumnos
    }
    @PostMapping("/delete/{dni}")
    public String deleteAlumno(@PathVariable String dni, RedirectAttributes redirectAttributes) {
        try {
            servicioAlumno.delete(dni);
            redirectAttributes.addFlashAttribute("successMessage", "Alumno eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el alumno.");
        }
        return "redirect:/gestion-alumnos";
    }
    @GetMapping("/edit/{dni}")
    public String showEditAlumnoModal(@PathVariable("dni") String dni, Model model) throws Exception {
        Alumno alumno = servicioAlumno.findbydni(dni);
        Persona persona = repositorioPersona.findById(dni).orElseThrow(() -> new Exception("Alumno no encontrado"));
        model.addAttribute("alumno", alumno);
        model.addAttribute("persona", persona);
        model.addAttribute("openEditModal", true);
        return "gestional";
    }
}
