package com.rolandopalermo.cursos.entities;

import java.util.UUID;

public class Curso {

    private UUID id;
    private String nombre;
    private String instructor;

    public Curso() {
    }

    public Curso(String nombre, String instructor) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.instructor = instructor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }

}
