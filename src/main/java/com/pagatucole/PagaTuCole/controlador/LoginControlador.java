package com.pagatucole.PagaTuCole.controlador;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/pv")
@RequiredArgsConstructor
public class LoginControlador {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "private admin";
    }
}
