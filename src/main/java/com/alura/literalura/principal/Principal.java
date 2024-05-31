package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.IAutorRepository;
import com.alura.literalura.repository.ILibroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class Principal{
    //variables e instancias
    private int opcion = -1;
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    @Autowired
    private ILibroRepository libroRepo;
    @Autowired
    private IAutorRepository autorRepo;

    //Construtor que recibe las interfaces inyectadas. Creamos atributos de las interfaces inyectadas
    public Principal(ILibroRepository libroRepository, IAutorRepository autorRepository){
        this.libroRepo = libroRepository;
        this.autorRepo = autorRepository;
    }

    //Metodo que muestra el menu al usuario
    public void muestraMenu(){
        do {
            try { //excepcion para cuando el usuario ingrese una opcion no valida
                System.out.println("""
                \n***** BIENVENIDO A LITERALURA *****
                Teclea el numero de la opción deseada:
                1. Buscar libro por título
                2. Listar libros registrados en la BD
                3. Listar autores registrados en la BD
                4. Listar autores vivos en un determinado año
                5. Listar libros por idioma
                0. Salir
                """);
                opcion = teclado.nextInt();
                teclado.nextLine(); //se coloca para evitar error

                switch (opcion){
                    case 1:
                        buscarLibroPortitulo();
                        break;
                    case 2:
                        listarLibrosRegistradosBD();
                        break;
                    case 3:
                        listarAutoresRegistradosBD();
                        break;
                    case 4:
                        System.out.println("Introduce un año para saber que autores estaban o estan vivos: ");
                        var year = teclado.nextInt();
                        buscarAutoresVivosPorAnio(year);
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
                        listarLibrosPorIdioma(idioma);
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación... Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e){
                System.out.println("Error: Ingresar una opción válida.");
                teclado.nextLine();
            }
        } while (opcion != 0);
    }


    //Metodo para buscar libro en la Api
    //Agregamos los parametros indicados a la URL_BASE que se envia al metodo obtenerDatosApi para poder buscar libros.
    //Los resultados que coinciden con las palabras del libro a buscar se guardan en la variable json
    //Despues se guardan todos los libros encontrados, en una clase de tipo JsonResults que contiene un List<DatosLibro>
    private JsonResults buscarLibroApi() {
        System.out.println("Introduce el titulo completo o parte del libro a buscar: ");
        var buscarLibro = teclado.nextLine();
        System.out.println("Buscando libro, espere...");
        String json = consumoApi.obtenerDatosApi(URL_BASE + "?search=" + buscarLibro.replace(" ", "%20"));
        JsonResults librosEncontradosApi = convierteDatos.JsonToClass(json, JsonResults.class);
        return librosEncontradosApi;
    }

    //En un objeto de tipo JsonResults se guarda la lista de libros encontrados del metodo buscarLibroApi()
    //if librosEncontrdosApi es diferente de vacio, se instancia un objeto de DatosLibro y se obtiene el primer libro de la lista
    //se instancia un objeto de tipo DatosAutor y se obtiene el primer autor de datosLibro
    //Se instancia un objeto de tipo Libro para almacenar una busqueda por titulo de libro en la BD usando el metodo de ILibroRepository
    //if libroDb existe, se imprime el libro de la BD
    //else libroDb esta vacio, se instancia un objeto de Autor para almacenar una busqueda por nombre de Autor en la BD
    //if autorDb esta vacio, se instancia objeto de Autor y se crea con los datosAutor, se guarda en la BD usando el metodo save del repositorio
    //Se instancia tambien objeto de Libro y se crea con datosLibro y objeto Autor
    //else autorDb existe, se instancia solamente objeto de Libro y se crea con datosLibro y autorDb
    private void buscarLibroPortitulo() {
        JsonResults librosEncontradosApi = buscarLibroApi();
        if( !librosEncontradosApi.libro().isEmpty() ){
            DatosLibro datosLibro = librosEncontradosApi.libro().get(0);
            DatosAutor datosAutor = datosLibro.autor().get(0);

            Libro libroDb = libroRepo.findByTitulo(datosLibro.titulo());
            if(libroDb != null){
                System.out.println("\n---LIBRO ENCONTRADO EN LA BD---");
                System.out.println(libroDb);
            }
            else{
                Autor autorDb = autorRepo.findByNombre(datosAutor.nombre());
                if(autorDb == null){
                    Autor autor = new Autor(datosAutor);
                    autorRepo.save(autor);
                    Libro libro = new Libro(datosLibro, autor);
                    libroRepo.save(libro);
                    System.out.println("\n---LIBRO ENCONTRADO EN LA API---");
                    System.out.println(libro);
                }
                else{
                    Libro libro = new Libro(datosLibro, autorDb);
                    libroRepo.save(libro);
                    System.out.println("\n---LIBRO ENCONTRADO EN LA API--- ");
                    System.out.println(libro);
                }
            }
        }
        else{
            System.out.println("---Libro no encontrado :(");
        }
    }

    //Creamos una lista de tipo Libro para almacenar el resultado del metodo del repositorio de libro
    //Aplicamos stream a la lista librosBD, comparamos por Id para ordenarlos conforme fueron agregadis a
    //la BD
    private void listarLibrosRegistradosBD(){
        List<Libro> librosBD = libroRepo.findAll();
        System.out.println("\n---LISTA DE LIBROS REGISTRADOS EN LA BD---");
        if (librosBD.isEmpty()){
            System.out.println("Lista de libros vacía, aun no se han realizado busquedas de libros");
        } else{
            librosBD.stream()
                    .sorted((Comparator.comparing(Libro::getId)))
                    .forEach(System.out::println);
            System.out.println(librosBD.size() +" libro(s) encontrado(s) en total.");
        }
    }

    //el metodo findAll() no se declaro, puesto que ya existe de forma predeterminada en
    //el repositorio por detras. Se pudo hacer lo mismo con listarlibrosRegistradosBD()
    private void listarAutoresRegistradosBD(){
        List<Autor> autoresDB = autorRepo.findAll();
        System.out.println("\n---LISTA DE AUTORES REGISTRADOS---");
        if(autoresDB.isEmpty()){
            System.out.println("Lista de autores vacía, posiblemente porque aun no se han realizado busqueda de libros");
        } else{
            autoresDB.stream()
                    .sorted(Comparator.comparing(Autor::getNombre))
                    .forEach(System.out::println);
            System.out.println(autoresDB.size() +" autor(es) encontrado(s) en total.");
        }
    }

    //Se declara una variable long para obtener el numero de autores que pasaron el .filter
    //filter deja pasar a cada autor que su año de nacimiento sea diferente de cero y que no tenga mas de 100 años de edad
    //peek es una operacion intermedia que se utiliza para realizar alguna accion en cada elemento del stream sin
    //consumirlo. Aqui se usa para imprimir los autores que pasan el filtro
    //count es una operacion terminal que devuelve el numero de elementos del stream despues de aplicar sus
    //operaciones, en este caso el filter
    private void buscarAutoresVivosPorAnio(int year){
        List<Autor> busquedaAutoresVivos = autorRepo.buscarAutorVivoPorAnio(year);
        System.out.println("\n---AUTORES VIVOS EN EL AÑO " +year +"---");
        if (busquedaAutoresVivos.isEmpty()){
            System.out.println("No se encontraron autores vivos de la BD en " +year +"\n");
        } else {
            long cantidadAutores = busquedaAutoresVivos.stream()
                    .filter(a -> a.getAnioNacimiento() != 0 && (year - a.getAnioNacimiento()) < 100)
                    .peek(a -> System.out.println(a.getNombre() + " (" + a.getAnioNacimiento() + " - " + a.getAnioDefuncion() + ")"))
                    .count();
            System.out.println("\n" +cantidadAutores +" autor(es) encontrados en total.");
        }
    }

    //La validacion de letras mayusculas o minusculas se hace en el repositorio
    private void listarLibrosPorIdioma(String idioma){
        List<Libro> librosPorIdioma = libroRepo.buscarLibrosPorIdioma(idioma);
        System.out.println("\n---LISTA DE LIBROS EN IDIOMA "+idioma.toUpperCase() +"---");
        if (librosPorIdioma.isEmpty()){
            System.out.println("No se encontraron libros en este idioma :(\n");
        } else {
            librosPorIdioma.stream()
                    .sorted((Comparator.comparing(Libro::getId)))
                    .forEach(System.out::println);
            System.out.println(librosPorIdioma.size() +" libro(s) encontrado(s) en total.");
        }
    }


}
