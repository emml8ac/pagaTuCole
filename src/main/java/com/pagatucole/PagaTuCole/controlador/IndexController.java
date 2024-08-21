package com.pagatucole.PagaTuCole.controlador;

import com.pagatucole.PagaTuCole.modelo.SecurityUser;
import com.pagatucole.PagaTuCole.modelo.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class IndexController {
    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Usuario usuario = securityUser.getUsuario();
        String nombre = usuario.getPersona().getNombre()+" "+usuario.getPersona().getApPaterno()+" "+usuario.getPersona().getApMaterno();
        model.addAttribute("username", nombre);
        model.addAttribute("Grado",usuario.getPersona().getEmail());
        return "index";
    }

}
