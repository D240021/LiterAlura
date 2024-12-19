package com.exampleLiterAlura.LiterAlura.Datos.Repositorios;

import com.exampleLiterAlura.LiterAlura.Dominio.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepositorio extends JpaRepository<Libro,Integer> {

    public List<Libro> findLibroByIdiomas(String idioma);
}
