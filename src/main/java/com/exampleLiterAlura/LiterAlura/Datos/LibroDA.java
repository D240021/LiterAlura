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
            return respuesta.getResults().get(0);
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
            // Persistir los autores si no están en la base de datos
            if (libro.getAutores() != null && !libro.getAutores().isEmpty()) {
                for (Autor autor : libro.getAutores()) {
                    // Si el autor aún no está en la base de datos, lo guardamos
                    if (autor.getId() == null) {
                        autorRepositorio.save(autor);
                    }
                }
            }

            // Guardar el libro (esto guardará el libro y sus autores si no están en la base de datos)
            libroRepository.save(libro); //El problema se encuentra en esta línea
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }



}
