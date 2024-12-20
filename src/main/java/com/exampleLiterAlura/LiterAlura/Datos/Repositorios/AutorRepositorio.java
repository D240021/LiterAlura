package com.exampleLiterAlura.LiterAlura.Datos.Repositorios;

import com.exampleLiterAlura.LiterAlura.Dominio.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepositorio extends JpaRepository<Autor,Integer> {

    @Query("SELECT au FROM Autor au WHERE :anio >= au.anioNacimiento AND :anio < au.anioFallecimiento")
    public List<Autor> obtenerAutoresVivosEnUnAnio(int anio);

    public boolean existsByNombre(String nombre);

}
