package com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Clase que contiene el metodo para convertir el json que tenemos en String a una clase e
//implementamos de la interfaz que creamos
public class ConvierteDatos implements IConvierteDatos{

    //Creamos una instancia de ObjectMapper para mapear el json usando su metodo. Tenemos que importar
    //la dependencia de jackson-databind en el pom.xml y actualizar los cambios en maven
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T JsonToClass(String json, Class<T> clase) {
        try {
            //retorna un objeto del tipo objectMapper. El metodo readValue lee el valor del
            // json y lo transforma a una clase
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
