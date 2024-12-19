package com.exampleLiterAlura.LiterAlura.Datos.Interfaces;

import com.exampleLiterAlura.LiterAlura.Dominio.Autor;
import com.exampleLiterAlura.LiterAlura.Dominio.Libro;

import java.util.List;

public interface IPrincipal {

    public Libro buscarLibroPorNombre();

    public void mostrarMenu();

    public void registrarNuevoLibro(Libro libro);

    public void obtenerLibrosRegistrados();

    public void obtenerAutoresRegistrados();

    public void obtenerAutoresVivosPorAnio();


}
