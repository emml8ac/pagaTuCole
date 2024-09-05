package com.pagatucole.PagaTuCole.controlador;

import com.pagatucole.PagaTuCole.modelo.Alumno;
import com.pagatucole.PagaTuCole.modelo.SecurityUser;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import com.pagatucole.PagaTuCole.repositorio.RepositorioAlumno;
import com.pagatucole.PagaTuCole.servicios.ServicioAlumno;
import com.pagatucole.PagaTuCole.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private RepositorioAlumno repositorioAlumno;
    @Autowired
    private ServicioUsuario servicioUsuario;
    @GetMapping
    public String mainPerfil(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Usuario usuario = securityUser.getUsuario();
        Alumno alumno = repositorioAlumno.findByDni(usuario.getUserid());
        model.addAttribute("usuario",usuario);
        model.addAttribute("alumno",alumno);
        return "perfil";
    }
    @PostMapping("/update")
    public String cambiarContrasena(@RequestParam("password") String nuevaContrasena,
                                    @RequestParam("confirmPassword") String confirmarContrasena,
                                    RedirectAttributes redirectAttributes) {
        if (nuevaContrasena.equals(confirmarContrasena)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            Usuario usuario = securityUser.getUsuario();
            servicioUsuario.cambiarContrasena(usuario, nuevaContrasena);
            redirectAttributes.addFlashAttribute("successMessage", "Contraseña actualizada correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Las contraseñas no coinciden.");
        }
        return "redirect:/perfil";
    }
}
