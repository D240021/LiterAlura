package com.exampleLiterAlura.LiterAlura.Datos;

import com.exampleLiterAlura.LiterAlura.Datos.Interfaces.IAutor;
import com.exampleLiterAlura.LiterAlura.Datos.Repositorios.AutorRepositorio;
import com.exampleLiterAlura.LiterAlura.Dominio.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorDA implements IAutor {

    private final AutorRepositorio autorRepositorio;

    @Autowired
    public AutorDA(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    @Override
    public List<Autor> obtenerTodosAutores() {
        return this.autorRepositorio.findAll();
    }

    @Override
    public List<Autor> obtenerAutoresVivosPorAnio(int anio) {
        return this.autorRepositorio.obtenerAutoresVivosEnUnAnio(anio);
    }
}
