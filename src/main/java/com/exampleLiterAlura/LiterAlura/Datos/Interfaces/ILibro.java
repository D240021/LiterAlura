package com.exampleLiterAlura.LiterAlura.Datos.Interfaces;

import com.exampleLiterAlura.LiterAlura.Dominio.Libro;

import java.util.List;

public interface ILibro {


    public Libro obtenerLibroPorNombre(String nombre);

    public List<Libro> obtenerLibrosRegistrados();

    public boolean registrarNuevoLibro(Libro libro);

    public List<Libro> obtenerLibrosPorIdioma(String idioma);



}
