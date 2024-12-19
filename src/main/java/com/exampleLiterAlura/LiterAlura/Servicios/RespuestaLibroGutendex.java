package com.exampleLiterAlura.LiterAlura.Servicios;

import com.exampleLiterAlura.LiterAlura.Dominio.Libro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaLibroGutendex {

    @JsonProperty("results")
    private List<Libro> results;

    public List<Libro> getResults() {
        return results;
    }

    public void setResults(List<Libro> results) {
        this.results = results;
    }

}
