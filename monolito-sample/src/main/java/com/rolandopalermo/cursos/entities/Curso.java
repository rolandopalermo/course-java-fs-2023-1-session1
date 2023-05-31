package com.rolandopalermo.cursos.entities;

public class Curso {

    private long id;
    private String nombre;
    private String instructor;

    public Curso() {
    }

    public Curso(long id, String nombre, String instructor) {
        this.id = id;
        this.nombre = nombre;
        this.instructor = instructor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

}
