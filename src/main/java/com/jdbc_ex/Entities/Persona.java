package com.jdbc_ex.Entities;

public class Persona {
    private static Long nextId = 1L; 
    private Long id;
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.id = nextId++; 
        this.nombre = nombre;
        this.edad = edad;
    }

     public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}