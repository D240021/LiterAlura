package com.exampleLiterAlura.LiterAlura.Dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @JsonProperty("title")
    private String nombre;

    @JsonProperty("authors")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id")
    )
    private List<Autor> autores = new ArrayList<>();

    @JsonProperty("languages")
    private List<String> idiomas;

    @JsonProperty("download_count")
    private int numeroDescargas;

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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return
                "Nombre: '" + nombre + '\'' +
                " Autores:" + autores +
                " Idiomas: " + idiomas +
                " Numero de descargas: " + numeroDescargas;
    }
}
