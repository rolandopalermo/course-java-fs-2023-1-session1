package com.rolandopalermo.cursos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = {"", "/", "/index"})
    public String saludo(Model modelo) {
        modelo.addAttribute("mensaje", "Hola desde RP Consulting");
        return "index";
    }

}
