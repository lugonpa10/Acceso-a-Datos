package com.example.examen33;

import java.util.ArrayList;
import java.util.List;

public class Personaje {

    // Propiedades privadas
    private int id;
    private String nombre;
    private String nombreCompleto;
    private int edad;
    private String sexo;
    private String nacionalidad;
    private String descripcion;
    private int imagen; // puede ser R.drawable.imagen

    // Constructor
    public Personaje(int id, String nombre, String nombreCompleto, int edad,
                     String sexo, String nacionalidad, String descripcion, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getImagen() { return imagen; }
    public void setImagen(int imagen) { this.imagen = imagen; }

    // Método estático para obtener la lista de personajes
    public static List<Personaje> getDatos() {
        List<Personaje> lista = new ArrayList<>();

        lista.add(new Personaje(1, "Homero", "Homero Jay Simpson", 39, "Masculino", "Americano",
                "Padre de familia, trabaja en la planta nuclear de Springfield.", R.drawable.homer));
        lista.add(new Personaje(2, "Marge", "Marjorie Bouvier Simpson", 36, "Femenino", "Americana",
                "Madre de familia, dedicada y cariñosa.", R.drawable.marge));
        lista.add(new Personaje(3, "Bart", "Bartholomew JoJo Simpson", 10, "Masculino", "Americano",
                "Hijo travieso y rebelde.", R.drawable.bart));
        lista.add(new Personaje(4, "Lisa", "Lisa Marie Simpson", 8, "Femenino", "Americana",
                "Hija inteligente y amante del saxofón.", R.drawable.lisa));
        lista.add(new Personaje(5, "Maggie", "Margaret Evelyn Simpson", 1, "Femenino", "Americana",
                "Bebé de la familia, casi nunca habla.", R.drawable.maggie));


        return lista;
    }
}
