# Test User Bank Smart Job

Este es un proyecto desarrollado en **Java 17** que implementa una API REST para la gestión de usuarios. Está basado en
la **arquitectura hexagonal**, y utiliza **Mockito** y **JUnit 5** para las pruebas unitarias, así como **Hibernate**
para la interacción con la base de datos. El proyecto también cuenta con la librería **FlywayDB** para la gestión de
migraciones de base de datos.

## Tecnologías utilizadas:

- **Java 17**
- **Spring Boot**
- **Hibernate** para la persistencia de datos
- **H2** como base de datos en memoria
- **FlywayDB** para la gestión de versiones y migraciones de base de datos
- **JUnit 5** y **Mockito** para pruebas unitarias

## Arquitectura:

El proyecto sigue una **arquitectura hexagonal**, con interfaces claras para la persistencia de datos (
`UserPersistence`) y casos de uso (`CreateUserUseCase`, `FindByUserUseCase`).


name: Nombre del usuario (obligatorio).
email: Correo electrónico del usuario (obligatorio y único).
password: Contraseña del usuario (obligatoria).
phones: Lista de teléfonos del usuario. Cada teléfono tiene un número, un código de ciudad (cityCode) y un código de país (countryCode) (todos obligatorios).

Todos los campos son obligatorios para crear un usuario exitosamente.

**Base de datos**

El proyecto utiliza una base de datos en memoria H2, ideal para pruebas y desarrollo rápido. Además, se gestiona la base de datos con la librería FlywayDB, que se encarga de crear y versionar los scripts SQL de migración.
FlywayDB

Cuando el proyecto se inicia, FlywayDB ejecuta los scripts SQL ubicados en la siguiente ruta:


src/main/resources/db/migration

Estos scripts se versionan y se ejecutan solo una vez para garantizar la consistencia de la base de datos. FlywayDB es responsable de aplicar las migraciones necesarias cada vez que el proyecto se levanta, asegurando que la estructura de la base de datos esté actualizada.
Scripts de base de datos

Los scripts de creación de tablas y otras operaciones relacionadas con la base de datos están en la carpeta resources/db/migration bajo el esquema de numeración que sigue Flyway. Por ejemplo:


V1__Create_user_table.sql

Estos archivos contienen las sentencias SQL que Flyway ejecuta para crear y modificar las tablas.
Pruebas

El proyecto incluye pruebas unitarias utilizando JUnit 5 y Mockito. Estas herramientas permiten crear simulaciones (mocks) de dependencias para realizar pruebas aisladas de la lógica de la aplicación, sin necesidad de acceder a la base de datos real ni realizar llamadas a servicios externos.
Ejecutar las pruebas

Puedes ejecutar las pruebas unitarias con el siguiente comando:

./gradlew test

Las pruebas verifican:

La correcta creación de usuarios.
La validación de campos obligatorios.
La interacción con las capas de persistencia mediante el uso de Mockito.

## Endpoints

### Crear usuario

La API cuenta con una ruta principal para la creación de usuarios:

**URL**: `http://localhost:8080/api/v1/users`  
**Método**: `POST`

#### Cuerpo de la solicitud:

````json
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "1234",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ]
}
