package com.pagatucole.PagaTuCole.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagos")
public class PagoUserController {
    @GetMapping
    public String principalPagos() {
        return "cronograma";
    }
}
