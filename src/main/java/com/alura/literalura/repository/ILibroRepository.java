package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
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

    //Metodo para encontrar un libro por el titulo. Inicia con la palabra findBy, agregamos el atributo titulo y
    //recibe como parametro un String con el titulo a buscar
    Libro findByTitulo(String titulo);

    //Metodo que devuelve una lista de tipo Libro con todos los libros almacenados en la BD
    List<Libro> findAll();

}
