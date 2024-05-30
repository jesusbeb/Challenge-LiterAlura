package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);

    //Metodo que usa una consulta JPQL, para buscar autores vivos en un determinado año y tambien en el caso que actualmente esten vivos
    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :year AND (a.anioDefuncion = 0 OR a.anioDefuncion >= :year)")
    List<Autor> buscarAutorVivoPorAnio(int year);

}
