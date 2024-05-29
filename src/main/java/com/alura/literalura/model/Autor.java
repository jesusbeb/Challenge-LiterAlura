package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

//OneToMany. mappedBy = "autor" indica una relacion bidireccional y se relaciona con el campo autor dentro de
//la entidad Libro. CascadeType.ALL propaga cualquier modificacion desde esta entidad a todas las
//entidades relacionadas. Si se elimina un autor, se eliminan todos los libros relacionados. EAGER define la
//estrategia de carga de las entidades relacionadas. EAGER carga inmediatamente los libros relacionados junto con
//los autores cuando se recuperan de la BD. La otra alternativa es LAZY que cargaria los libros solo si se accede
//explicitamente a ellos.
@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int anioNacimiento;
    private int anioDefuncion;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    //constructores
    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioDefuncion = datosAutor.anioDefuncion();
    }

    //getter ands setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioDefuncion() {
        return anioDefuncion;
    }

    public void setAnioDefuncion(int anioDefuncion) {
        this.anioDefuncion = anioDefuncion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    //Para mostrar solo el titulo de los libros de la lista libros, aplicamos stream a la lista,
    //mapeamos cada objeto Libro y obtenemos la propiedad titulo
    //collect(Collectors.joining... junta todos los titulos en una cadena y los separa por comas
    //almacenamos todo en el String librosTitulos, e imprimimos el String en el retorno de toString
    @Override
    public String toString() {
        String librosTitulos = libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));

        return "Autor: " +nombre +"\n" +
                "Año de nacimiento: " +anioNacimiento +"\n" +
                "Año de fallecimiento: " +anioDefuncion +"\n" +
                "Libros: " +librosTitulos +"\n";
    }
}

