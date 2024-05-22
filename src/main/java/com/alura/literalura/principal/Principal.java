package com.alura.literalura.principal;

import com.alura.literalura.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    //variables
    private int opcion = -1;
    private Scanner teclado = new Scanner(System.in);
    ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";






    //Metodo que muestra el menu al usuario
    public void muestraMenu(){

        String json = consumoApi.obtenerDatosApi(URL_BASE);
        System.out.println(json);


//        do {
//            System.out.println("""
//                Teclea el numero de la opción deseada:
//                1. Buscar libro por título
//                2. Listar libros registrados
//                3. Listar autores registrados
//                4. Listar autores vivos en un determinado año
//                5. Listar libros por idioma
//                0. Salir
//                """);
//            opcion = teclado.nextInt();
//
//            switch (opcion){
//                case 1:
//                    System.out.println("Introduce el título del libro a buscar: ");
//                    //buscarLibroPortitulo()
//                    break;
//                case 2:
//                    System.out.println("LISTA DE LIBROS REGISTRADOS: ");
//                    //listarLibrosRegistrados()
//                    break;
//                case 3:
//                    System.out.println("LISTA DE AUTORES REGISTRADOS: ");
//                    //listarAutoresRegistrados()
//                    break;
//                case 4:
//                    System.out.println("Introduce el año para mostrar los autores vivos en ese año: ");
//                    var year = teclado.nextInt();
//                    //autoresVivos();
//                    break;
//                case 5:
//                    System.out.println("""
//                        Ingrese el idioma para buscar los libros:
//                        es - español
//                        en - inglés
//                        fr - francés
//                        pt - portugués
//                        """);
//                    var idioma = teclado.next();
//                    teclado.nextLine();
//                    //listarLibrosPorIdioma();
//                case 0:
//                    System.out.println("Saliendo de la aplicación... Hasta luego!");
//                    break;
//                default:
//                    System.out.println("Opción inválida.");
//            }
//        } while (opcion != 0);
    }


}
