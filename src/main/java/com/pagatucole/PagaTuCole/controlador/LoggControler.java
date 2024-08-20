package com.pagatucole.PagaTuCole.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoggControler {
    @GetMapping("/")
    String principal() {
        return "principal";
    }
    @GetMapping("/login")
    String login() {
        return "login";
    }
    @GetMapping("/index")
    String index(){
        return "index";
    }
}
