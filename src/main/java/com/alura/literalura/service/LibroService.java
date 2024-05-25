package com.alura.literalura.service;

import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.JsonResults;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Service para indicar que es una clase de servicio y trabajar con las reglas de
//negocio como buena practica. El pack service hace la transformacion de datos,
//operaciones, llamar al repository, contener los metodos, etc.
@Service
public class LibroService {
    //variables e instancias
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();


    //Metodo para buscar libro en la Api
    //Agregamos los parametros indicados a la URL_BASE que se envia al metodo obtenerDatosApi para poder buscar libros.
    //Guardamos los resultados de la busqueda en la Api en datosBusqueda
    //Creamos la variable local Optional de tipo DatosLibro que es el contenedor que almacenara el resultado si es encontrado o no.
    //datosbusqueda es de tipo JsonResults, accesamos a la lista que tiene de libros y aplicamos stream
    //filtramos la lista de libros para que solo contenga los que coincidan con el libro buscado por el usuario
    //find.First obtiene el primer resultado. Se trata la variable Optional con if-else
    public void buscarLibroPortitulo(String buscarLibro){
        var json = consumoApi.obtenerDatosApi(URL_BASE + "?search" + buscarLibro.replace(" ", "%20"));
        var datosBusqueda = convierteDatos.JsonToClass(json, JsonResults.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.libro().stream()
                .filter(l -> l.titulo().toUpperCase().contains(buscarLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado: " +libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado.");
        }

    }



}