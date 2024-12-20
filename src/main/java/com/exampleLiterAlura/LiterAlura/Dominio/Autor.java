package com.exampleLiterAlura.LiterAlura.Dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("birth_year")
    private Integer anioNacimiento;

    @JsonProperty("death_year")
    private Integer anioFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros = new ArrayList<>();
    ;

    @Version
    private Integer version = 0;

    //Getters, setters y toString abajo

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public void setUnicoLibro(Libro libro){
        this.libros.add(libro);
    }

    @Override
    public String toString() {
        return
                " Nombre: '" + nombre + '\'' +
                ", Año de nacimiento: " + anioNacimiento +
                ", Año de fallecimiento=" + anioFallecimiento;
    }
}
