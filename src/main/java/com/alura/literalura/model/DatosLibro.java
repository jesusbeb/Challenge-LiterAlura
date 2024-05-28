package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//Creamos clase record de Datoslibro para obtener los datos del libro deseado y que estan dentro de la clave results del json
//authors lo almacenamos en una lista, ya que en el json esta igual en formato de lista. La lista sera
//de tipo DatosAutor y se creara dicha clase para mapear los datos que traiga
//languages tambien esta en una lista, pero se creara de String, ya que no se mapearan sus datos
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") int descargas
){
}
