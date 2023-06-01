package com.rolandopalermo.cursos.controllers;

import com.rolandopalermo.cursos.entities.Curso;
import com.rolandopalermo.cursos.services.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cursos")
public class CursosController {

    private final CursoService cursoService;

    public CursosController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @RequestMapping
    public String listar(Model modelo) {
        modelo.addAttribute("cursos", cursoService.listar());
        return "cursos";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") long id) {
        cursoService.eliminar(id);
        return "redirect:/cursos";
    }

    @RequestMapping("/nuevo")
    public String mostrarNuevo(Model model) {
        Curso curso = new Curso();
        model.addAttribute("curso", curso);
        return "crear-curso";
    }

    @RequestMapping("/agregar")
    public String agregar(@ModelAttribute("curso") Curso curso) {
        cursoService.agregar(curso);
        return "redirect:/cursos";
    }

}
