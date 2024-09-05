package com.pagatucole.PagaTuCole.controlador;

import com.pagatucole.PagaTuCole.modelo.*;
import com.pagatucole.PagaTuCole.repositorio.RepositorioAlumno;
import com.pagatucole.PagaTuCole.repositorio.RepositorioConcepto;
import com.pagatucole.PagaTuCole.repositorio.RepositorioConceptoMes;
import com.pagatucole.PagaTuCole.repositorio.RepositorioMes;
import com.pagatucole.PagaTuCole.servicios.ServicioCronograma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/gestion-pagos")
public class PagoController {
    @Autowired
    private ServicioCronograma cronogramaService;

    @Autowired
    private RepositorioAlumno alumnoRepository;

    @Autowired
    private RepositorioConcepto conceptoRepository;

    @Autowired
    private RepositorioMes mesRepository;
    @Autowired
    private RepositorioConceptoMes repositorioConceptoMes;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String paginaPagos(Model model) {
        List<Alumno> alumnos = alumnoRepository.findAll();
        List<Concepto> conceptos = conceptoRepository.findAll();
        List<Mes> meses = mesRepository.findAll();

        // Agregar las listas al modelo para que estén disponibles en el modal
        model.addAttribute("alumnos", alumnos);
        model.addAttribute("conceptos", conceptos);
        model.addAttribute("meses", meses);
        return "gestion-pagos";
    }

    @PostMapping("/guardar")
    public String guardarCronograma(@RequestParam("alumnoId") String alumnoId,
                                    @RequestParam("conceptoId") Long conceptoId,
                                    @RequestParam("mesId") Long mesId,
                                    @RequestParam("estado") String estado,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Verifica si el alumno existe
            Alumno alumno = alumnoRepository.findByDni(alumnoId);

            // Verifica si el concepto existe
            Concepto concepto = conceptoRepository.findById(conceptoId)
                    .orElseThrow(() -> new IllegalArgumentException("Concepto no encontrado"));

            // Verifica si el mes existe
            Mes mes = mesRepository.findById(mesId)
                    .orElseThrow(() -> new IllegalArgumentException("Mes no encontrado"));

            // Busca si ya existe una relación entre el concepto y el mes
            ConceptoMes conceptoMes = repositorioConceptoMes.findByConceptoIdAndMesId(conceptoId, mesId);

            if (conceptoMes == null) {
                // Si no existe, crea una nueva relación
                conceptoMes = new ConceptoMes();
                conceptoMes.setConcepto(concepto);
                conceptoMes.setMes(mes);
                conceptoMes = repositorioConceptoMes.save(conceptoMes);
            }

            // Crear y guardar el cronograma de pago
            cronogramaService.crearCronograma(alumnoId, conceptoMes.getId(), estado);


            redirectAttributes.addFlashAttribute("successMessage", "Cronograma creado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al crear el cronograma: " + e.getMessage());
        }
        return "redirect:/gestion-pagos";
    }
}
