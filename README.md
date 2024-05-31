<h1> Challengue: Literalura </h1>

<h2>Descripción</h2>
Este proyecto es un Challenge o Desafío para crear una aplicacion en consola, que permite buscar libros en una API y almacenar esas busquedas localmente en una Base de Datos. Esto como parte del programa de Oracle Next Education y Alura Latam.

<h2>Funciones disponibles</h2>
Las funcionalidades que tiene esta aplicacion se describen a continuacion:
<ol>
  <li>
   <h3>Buscar libros por titulo</h3>
    <p>Esta opcion busca por el nombre de libro que el usuario ingrese. La aplicacion primero busca en la API de Gutendex (https://gutendex.com/) si no encuentra un resultado, se lo indica al usuario. En caso de encontrar un resultado, pasa a comprobar
      si el libro ya habia sido buscado en la API, realizando una consulta a la BD. Si el libro aparece ya en la BD, simplemente obtiene la informacion de esta y la muestra al usuario; si la consulta del libro no aparece en la BD, almacena
      entonces esta consulta en la BD y de ese almacenamiento obtiene la informacion del libro para mostrarla al usuario.</p>
  </li>
  
  <li>
    <h3>Listar libros registrados en la BD</h3>
    <p>Esta opcion hace una consulta a la BD y muestra todos los libros almacenados en esta, mostrando el nombre del libro, autor, idioma en el que esta escrito y numero de descargas que ha tenido. Al final muestra el total de libros almacenados en la BD.</p>
  </li>
  
  <li>
    <h3>Listar autores registrados en la BD</h3>
    <p>Esta opcion hace una consulta a la BD y muestra todos los autores almacenados en esta, sin que aparezcan repetidos cuando estos han escrito mas de un libro. La consulta muestra el nombre del autor, año de nacimiento y fallecimiento y los libros que
    ha escrito y que estan almacenados en la BD. Al final se muestra el total de autores registrados en la BD.</p>
  </li>
  
  <li>
    <h3>Listar autores vivos en un determinado año</h3>
    <p>Esta opcion solicita al usuario que ingrese un año para mostrarle el nombre de los autores vivos en ese año. Se aceptan el año cero y años negativos, considerando a los autores que vivieron Antes de Cristo. Al final se muestra el total de autores
    encontrados de la BD.</p>
  </li>
  
  <li>
    <h3>Listar libros por idioma</h3>
    <p>Esta opcion solicita al usuario que ingrese la abreviacion de uno de los 4 idiomas disponibles (en - inglés, es - español, fr - francés, pt - portugués) para consultar los libros escritos en tal idioma. Al final se muestra el total de libros
    encontrados en el idioma solicitado.</p>
  </li>
</ol>

<h2>Cómo usarlo</h2>
Para hacer uso de la aplicacion, se aconseja seguir los siguientes pasos: 
<ol>
  <li>
    <p>Descargar el repositorio y descomprimirlo en alguna carpeta.</p>
  </li>
  
  <li>
    <p>Se recomienda usar IntelliJ para abrir como proyecto la carpeta descargada.</p>
  </li
    
  <li>
    <p>Una vez abierto el proyecto en IntelliJ, actualizar las dependencias de Maven para que se carguen en el proyecto.</p>
  </li
    
  <li>
    <p>Se recomienda usar PostgreSQL y en pgAdmin crear una Base de Datos sin Tablas para almacenar los datos que la aplicacion obtendra de la API.</p>
  </li
    
  <li>
    <p>Configurar el archivo application.properties del proyecto para indicar la url de la BD, username, password, driver de postgresql y dialecto de hibernate
     Ejemplo de configuracion necesaria:</p>
    
     #url de la BD donde trabajaremos
     spring.datasource.url=jdbc:postgresql://localhost/alura_series
     spring.datasource.username=postgres
     spring.datasource.password=contraseña
     spring.datasource.driver-class-name=org.postgresql.Driver
     hibernate.dialect=org.hibernate.dialect.HSQLDialect
     #Se indica a JPA que tiene permiso para ver cambios en la app y reflejarlos en la BD
     spring.jpa.hibernate.ddl-auto=update
  </li

  <li>
    <p>Finalmente se puede ejecutar la aplicacion para usarla.</p>
  </li>
</ol>

<h3>Tecnologias usadas</h3>
<ul>
  <li>Java</li>
  <li>Springboot</li>
  <li>Jackson Databind</li>
  <li>PostgreSQL</li>
  <li>JPA</li>
</ul>

<h3>Desarrollador del proyecto</h3>
<p>Jesús Beltrán B</p>
   
