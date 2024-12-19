package com.exampleLiterAlura.LiterAlura.Datos.Repositorios;

import com.exampleLiterAlura.LiterAlura.Dominio.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro,Integer> {
}
