package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//Creamos una clase record para obtener primero los datos de la clave "results" del json, ya que dentro de
//esta clave se encuentran otras claves con los datos que nos interesan. Estas claves que estan
//dentro de results se guardaran en un List<Libro>. Y se creara la clase Libro para que
//contenga las claves que estan dentro de "results"
//@JsonIgnoreProperties(ignoreUnknown = true) para que ignore las propiedades del json que no nos interesa mapear
//Usamos @JsonAlias para indicar entre parentesis el nombre tal y como esta en el json. Enseguida especificamos
//el tipo de variable y el nombre al que sera mapeado en java
@JsonIgnoreProperties(ignoreUnknown = true)
public record JsonResults(
        @JsonAlias("results") List<DatosLibro> libro
) {

}
