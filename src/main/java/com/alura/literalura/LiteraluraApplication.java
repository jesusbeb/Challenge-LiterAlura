package com.alura.literalura;

import com.alura.literalura.principal.Principal;
import com.alura.literalura.repository.IAutorRepository;
import com.alura.literalura.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Hacemos uso de la interfaz CommandLineRunner que permite realizar una accion cuando
//iniciemos la app, p.e. puede ser un mensaje en consola, cargar una BD...
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    //Autowired indica a Spring que haga una Inyeccion de Dependencias. Esto para poder
    //instanciar las interfaces ILibroRepository y IAutorRepository, ya que no se pueden instanciar de
    //manera simple como cualquier otra clase.
    @Autowired
    private ILibroRepository librorepository;
    @Autowired
    private IAutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

    //En la instancia de la clase principal pasamos los objetos de las Interfaces inyectadas, al
    //constructor, de esta manera ya podemos hacer uso de sus metodos en la clase Principal
    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(librorepository, autorRepository);
        principal.muestraMenu();
    }
}
