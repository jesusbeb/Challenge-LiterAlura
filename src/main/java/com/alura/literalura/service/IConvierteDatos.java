package com.alura.literalura.service;

//I se utiliza para identificar que es una interfaz
//Usamos una interfaz que trabaja con datos genericos, pensando en que la app se podria escalar en un futuro y
//trabjar con distintos objetos. En caso contrario tendriamos que crear diferentes metodos para trabajar con
//diferentes objetos, aumentando el codigo
public interface IConvierteDatos {

    //metodo
    //<T> T -- Esto indica que trabajamos con tipos de datos genericos. Retornara un generico
    //Class<T> recibe clase de tipo generico
    <T> T JsonToClass(String json, Class<T> clase);
}
