package com.alura.literalura.principal;

import com.alura.literalura.model.DatosAutor;
import com.alura.literalura.model.JsonResults;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    //variables e instancias
    private int opcion = -1;
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroService libroService = new LibroService();


    //Metodo que muestra el menu al usuario
    public void muestraMenu(){

//        //Usamos el metodo para consumir la Api y la almacenamos en un String
//        var json = consumoApi.obtenerDatosApi(URL_BASE);
//        //System.out.println(json);
//        //Usamos el metodo para convertir el json en String a una clase que contiene un List<Libro> e
//        //imprimimos la lista con todos los libros y sus atributos
//        //var datos = convierteDatos.JsonToClass(json, JsonResults.class);
//        //System.out.println(datos);

        do {
            System.out.println("""
                \nTeclea el numero de la opción deseada:
                1. Buscar libro por título
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                0. Salir
                """);
            opcion = teclado.nextInt();
            teclado.nextLine(); //se coloca para evitar error

            switch (opcion){
                case 1:
                    System.out.println("Introduce el titulo completo o parte del libro a buscar: ");
                    var buscarLibro = teclado.nextLine();
                    libroService.buscarLibroPortitulo(buscarLibro);
                    break;
                case 2:
                    System.out.println("LISTA DE LIBROS REGISTRADOS: ");
                    //listarLibrosRegistrados()
                    break;
                case 3:
                    System.out.println("LISTA DE AUTORES REGISTRADOS: ");
                    //listarAutoresRegistrados()
                    break;
                case 4:
                    System.out.println("Introduce el año para mostrar los autores vivos en ese año: ");
                    var year = teclado.nextInt();
                    //autoresVivos();
                    break;
                case 5:
                    System.out.println("""
                        Ingrese el idioma para buscar los libros:
                        es - español
                        en - inglés
                        fr - francés
                        pt - portugués
                        """);
                    var idioma = teclado.next();
                    teclado.nextLine();
                    //listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación... Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }


}
