package com.alura.literalura;

import com.alura.literalura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Hacemos uso de la interfaz CommandLineRunner que permite realizar una accion cuando
//iniciemos la app, p.e. puede ser un mensaje en consola, cargar una BD...
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.muestraMenu();
    }
}
