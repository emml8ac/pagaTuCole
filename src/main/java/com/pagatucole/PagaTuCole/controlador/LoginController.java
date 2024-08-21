package com.pagatucole.PagaTuCole.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    String principal() {
        return "principal";
    }
    @GetMapping("/login")
    String login() {
        return "login";
    }
    @GetMapping("/nopass")
    public String forgotPasss() {
        return "NoPass";
    }
}
