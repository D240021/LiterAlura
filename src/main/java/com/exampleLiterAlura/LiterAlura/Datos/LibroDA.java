package com.exampleLiterAlura.LiterAlura.Datos;

import com.exampleLiterAlura.LiterAlura.Datos.Interfaces.ILibro;
import com.exampleLiterAlura.LiterAlura.Datos.Repositorios.AutorRepositorio;
import com.exampleLiterAlura.LiterAlura.Datos.Repositorios.LibroRepositorio;
import com.exampleLiterAlura.LiterAlura.Dominio.Autor;
import com.exampleLiterAlura.LiterAlura.Dominio.Libro;
import com.exampleLiterAlura.LiterAlura.Servicios.RespuestaLibroGutendex;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibroDA implements ILibro {

    private final LibroRepositorio libroRepository;
    private final AutorRepositorio autorRepositorio;
    private final ObjectMapper objectMapper;
    private final ConsumoGutendex consumoGutendex;

    @Autowired
    public LibroDA(LibroRepositorio libroRepository, AutorRepositorio autorRepositorio) {
        this.libroRepository = libroRepository;
        this.autorRepositorio = autorRepositorio;
        this.objectMapper = new ObjectMapper();
        this.consumoGutendex = new ConsumoGutendex();
    }

    @Override
    public Libro obtenerLibroPorNombre(String nombre) {
        try {
            String nombreCodificado = URLEncoder.encode(nombre, StandardCharsets.UTF_8);
            String json = this.consumoGutendex.obtenerLibroPorNombre(nombreCodificado);
            RespuestaLibroGutendex respuesta = this.objectMapper.readValue(json, RespuestaLibroGutendex.class);
            return respuesta.getResults().isEmpty() ? null : respuesta.getResults().get(0);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Libro> obtenerLibrosPorIdioma(String idioma){
        try {
            String idiomaCodificado = URLEncoder.encode(idioma, StandardCharsets.UTF_8);
            String json = this.consumoGutendex.obtenerLibrosPorIdioma(idiomaCodificado);
            RespuestaLibroGutendex respuesta = this.objectMapper.readValue(json, RespuestaLibroGutendex.class);
            return respuesta.getResults();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Libro> obtenerLibrosRegistrados() {
        return this.libroRepository.findAll();
    }

    @Transactional
    @Override
    public boolean registrarNuevoLibro(Libro libro) {
        try {

            if(this.libroRepository.existsByNombre(libro.getNombre()))
                return false;

            Libro nuevoLibro = new Libro(libro.getNombre(),
                     libro.getIdiomas(), libro.getNumeroDescargas());

            List<Autor> autores = libro.getAutores();

            autores.stream()
                     .forEach( autor -> {

                                if(!this.autorRepositorio.existsByNombre(autor.getNombre()))
                                    nuevoLibro.setUnicoAutor(autor);
                             }
                     );

            libroRepository.save(nuevoLibro);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }





}
