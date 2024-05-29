package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

//Se agrega en el pom las dependencias de Spring Data JPA y PostgreSQL Driver
//Utilizamos anotaciones de hibernate para mapear las entidades
//Entity indica a JPA que la clase sera una tabla en la BD
//Table indica el nombre que tendra la tabla en la BD
//Id para indicar un atributo que sera el identificador en la tabla de la BD
//GeneratedValue se indica la estrategia de generacion del id. IDENTITY indica que sera incremental y JPA se encargara de eso
//@Transient indica a JPA que por el momento ignore el atributo y no cree la columna
@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    //@Transient
    @ManyToOne
    private Autor autor;
    private String idiomas;
    private int descargas;

    //constructor predeterminado. JPA exige tener este constructor, en todas las entidades.
    //Este constructor se crea automaticamente mientras no se cree un constructor personalizado. Una vez que se
    //crea un constructor personalizado, hay que crear este constructor manualmente
    public Libro(){}

    //constructor personalizado. Recibe un objeto de tipo datosLibro y un objeto de tipo Autor
    //Se toman los valores de los atributos de esos objetos que se reciben, para asignarlos a los atributos de esta clase
    //datosLibro.idiomas().get(0) toma el primer idioma de la lista de idiomas
    public Libro(DatosLibro datosLibro, Autor autor){
        this.titulo = datosLibro.titulo();
        this.autor = autor;
        this.idiomas = datosLibro.idiomas().get(0);
        this.descargas = datosLibro.descargas();
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    //Para mostrar la informaciona mapeada
    @Override
    public String toString() {
        return "Título: " +titulo +"\n" +
                "Autor: " +autor.getNombre() +"\n" +
                "Idioma: " +idiomas +"\n" +
                "Descargas: " +descargas +"\n";

//        return "titulo='" + titulo + '\'' +
//                ", autor=" + autor +
//                ", idiomas=" + idiomas +
//                ", numeroDeDescargas=" + descargas;
    }
}
