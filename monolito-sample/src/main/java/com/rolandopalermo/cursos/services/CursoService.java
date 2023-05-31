package com.rolandopalermo.cursos.services;

import com.rolandopalermo.cursos.entities.Curso;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    private List<Curso> cursos = new ArrayList<>();

    @PostConstruct
    public void postConstruct() {
        cursos.add(new Curso(1L, "Java FS", "Juan Perez"));
        cursos.add(new Curso(2L, "DevOps", "Jhon Smith"));
        cursos.add(new Curso(3L, "Facturación electrónica", "Rolando Rodríguez"));
    }

    public List<Curso> listar() {
        return cursos;
    }

    public void eliminar(long id) {
        cursos.removeIf(curso -> curso.getId() == id);
    }

    public void agregar(Curso curso) {
        cursos.add(curso);
    }

}
