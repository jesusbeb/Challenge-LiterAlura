package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Para poder trabajar con las operaciones basicas del CRUD en la BD, usamos un repositorio.
//Creamos una Interface con el nombre de la identidad que se quiere mapear (Libro), seguida del subfijo
//Repository. Y extiende de JpaRepository el cual pide tipo generico. Se le pasa la
//entidad a mapear (Libro) y el tipo del id (Long)
@Repository
public interface ILibroRepository extends JpaRepository<Libro, Long> {

    //Derived Querys o consultas derivadas. Son consultas que JPA interpretara al usar ciertas
    //palabras en el nombre del metodo.
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    //Metodo para encontrar un libro por el titulo. Inicia con la palabra findBy, agregamos el atributo titulo y
    //recibe como parametro un String con el titulo a buscar
    Libro findByTitulo(String titulo);

    //Metodo que devuelve una lista de tipo Libro con todos los libros almacenados en la BD
    List<Libro> findAll();

    //Metodo que usa una consulta JPQL para mostrar todos los libros escritos en cierto idioma
    //LOWER(:idioma) convierte el parametro idioma a minusculas suponiendo que el usuario lo ingrese en mayusculas, para
    //hacer coincidir la busqueda con el atributo idiomas de la entidad libro que esta en minusculas
    @Query("SELECT l FROM Libro l WHERE l.idiomas = LOWER(:idioma)" )
    List<Libro> buscarLibrosPorIdioma(String idioma);

}
