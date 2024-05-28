package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

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


    @Override
    public String toString() {
        return "nombre='" + nombre + '\'' +
                ", Año de Nacimiento='" + anioNacimiento + '\'' +
                ", Año de Defuncion='" + anioDefuncion + '\'';
    }
}

