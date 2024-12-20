package com.exampleLiterAlura.LiterAlura.Datos.Repositorios;

import com.exampleLiterAlura.LiterAlura.Dominio.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepositorio extends JpaRepository<Libro,Integer> {

    public boolean existsByNombre(String nombre);

}
