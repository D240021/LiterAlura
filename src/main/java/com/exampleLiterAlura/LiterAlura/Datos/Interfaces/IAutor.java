package com.exampleLiterAlura.LiterAlura.Datos.Interfaces;

import com.exampleLiterAlura.LiterAlura.Dominio.Autor;

import java.util.List;

public interface IAutor {

    public List<Autor> obtenerTodosAutores();

    public List<Autor> obtenerAutoresVivosPorAnio(int anio);
}
