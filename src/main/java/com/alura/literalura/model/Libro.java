package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

//Se agrega en el pom las dependencias de  Spring Data JPA y PostgreSQL Driver
//Utilizamos anotaciones de hibernate para mapear las entidades
//Entity indica a JPA que la clase sera una tabla en la BD
//Table indica el nombre que tendra la tabla en la BD
//Id para indicar un atributo que sera el identificador en la tabla de la BD
//GeneratedValue se indica la estrategia de generacion del id. IDENTITY indica que sera incremental y JPA se encargara de eso
//@Transient indica a JPA que por el momento ignore el atributo y no cree la columna
//Enumerated se le indica a JPA que es enum, una lista de otros atributos. Hay dos opciones: ORDINAL toma cada atributo del
//enum por su posicion y STRING toma todos los atributos como un solo texto
@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Transient
    private List<Autor> autor;
    private String idioma;


}
