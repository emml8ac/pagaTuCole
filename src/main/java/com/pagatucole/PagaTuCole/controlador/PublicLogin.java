package com.pagatucole.PagaTuCole.controlador;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicLogin {
    @GetMapping("/principal")
    public String index() {
        return "principal";
    }
}
