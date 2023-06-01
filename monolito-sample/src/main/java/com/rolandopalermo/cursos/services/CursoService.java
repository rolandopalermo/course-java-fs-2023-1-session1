package com.rolandopalermo.cursos.services;

import com.rolandopalermo.cursos.entities.Curso;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CursoService {

    private List<Curso> cursos = new ArrayList<>();

    @PostConstruct
    public void postConstruct() {
        cursos.add(new Curso("Java FS", "Juan Perez"));
        cursos.add(new Curso("DevOps", "Jhon Smith"));
        cursos.add(new Curso("Facturación electrónica", "Rolando Rodríguez"));
    }

    public List<Curso> listar() {
        return cursos;
    }

    public void eliminar(UUID id) {
        log.info("Eliminando curso con ID {}", id);
        cursos.removeIf(curso -> curso.getId().equals(id));
    }

    public void agregar(Curso curso) {
        log.info("Guardando curso con datos = {}", curso);
        cursos.add(curso);
    }

    public void actualizar(Curso cursoEditado) {
        int index = 0;
        for (Curso curso : cursos) {
            if (curso.getId().equals(cursoEditado.getId())) {
                break;
            }
            ++index;
        }
        log.info("Actualizando curso en la posición {} con nuevos datos = {}", index, cursoEditado);
        cursos.set(index, cursoEditado);
    }

    public Curso buscarPorId(UUID id) {
        return cursos.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst()
                .get();
    }

}
