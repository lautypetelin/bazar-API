# Bazar API

**Bazar-API** es una aplicación diseñada para gestionar de manera eficiente las operaciones de un bazar en línea. Permite realizar operaciones CRUD sobre productos, clientes y ventas, brindando una experiencia sencilla tanto para administradores como para usuarios.

## Características principales

- **Gestión de productos:** Añadir, editar, eliminar y consultar productos.
- **Gestión de clientes:** Administrar clientes: agregar, editar, eliminar y consultar.
- **Gestión de ventas:** Realizar operaciones sobre ventas y consultar resúmenes como ventas por fecha y la mayor venta realizada.
- **RESTful API:** Acceso a recursos mediante métodos HTTP.

## Endpoints principales

### Productos
- **Crear producto:** `POST localhost:8080/productos/crear`
- **Consultar productos:** `GET localhost:8080/productos`
- **Consultar producto por ID:** `GET localhost:8080/productos/1`
- **Eliminar producto:** `DELETE localhost:8080/productos/eliminar/1`
- **Editar producto:** `PUT localhost:8080/productos/editar/1`
- **Productos con stock bajo:** `GET localhost:8080/productos/falta_stock`

### Clientes
- **Crear cliente:** `POST localhost:8080/clientes/crear`
- **Consultar clientes:** `GET localhost:8080/clientes`
- **Consultar cliente por ID:** `GET localhost:8080/clientes/1`
- **Eliminar cliente:** `DELETE localhost:8080/clientes/eliminar/1`
- **Editar cliente:** `PUT localhost:8080/clientes/editar/1`

### Ventas
- **Crear venta:** `POST localhost:8080/ventas/crear`
- **Consultar ventas:** `GET localhost:8080/ventas`
- **Consultar venta por ID:** `GET localhost:8080/ventas/1`
- **Eliminar venta:** `DELETE localhost:8080/ventas/eliminar/1`
- **Editar venta:** `PUT localhost:8080/ventas/editar/1`
- **Consultar productos de una venta:** `GET localhost:8080/ventas/productos/1`
- **Ventas por fecha:** `GET localhost:8080/ventas/fecha/2025-03-08`
- **Mayor venta:** `GET localhost:8080/ventas/mayor_venta`

## Ejemplo de respuesta con Postman

![Ejemplo de respuesta con Postman](https://github.com/user-attachments/assets/79ab809d-1bb7-4e64-8402-e1dec6a08e7d)



## Colección Postman

La colección de Postman con todos los endpoints está disponible en la carpeta raíz del proyecto.

## Pruebas

Utiliza Postman para probar los endpoints de la API. Puedes importar la colección de Postman desde la carpeta raíz y ejecutar las pruebas.

## Configuración del entorno:

Asegúrate de tener Java 17 y Maven instalados.  
Configura la base de datos MySQL y crea una base de datos llamada `bazar`.

## Créditos

- **Desarrollado por**: Lautaro Tomás Israel Petelin
- **Tecnologías utilizadas**: Java 17.0.6, Spring Boot 3.4.2, Spring Web, Spring Data JPA, Hibernate, MySQL, Maven, Validations, Lombok
- **IDE**: Intellij IDEA 17
- **Autor de la consigna**: Luisina de Paula / TodoCode

## Contribuciones

¡Las contribuciones son bienvenidas! Si encuentras algún problema o tienes sugerencias de mejora, no dudes en abrir un Issue o enviar una pull request.

## Contacto
Puedes contactarme para preguntas o comentarios:

Email: lautisrra@gmail.com  
LinkedIn: [Lautaro Tomás Israel Petelin](https://www.linkedin.com/in/lautaro-petelin/)
