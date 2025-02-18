# Contact List API

## Descripción
Contact List API es una aplicación backend desarrollada en **Java 23** con **Spring Boot 3.4.2**, que proporciona una API REST para la gestión de contactos y usuarios. Actualmente, permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) tanto para contactos como para usuarios. La API utiliza **MySQL** como sistema de base de datos.

## Tecnologías utilizadas

Este proyecto utiliza las siguientes tecnologías y dependencias:

- **Spring Boot** (versión 3.4.2): Framework principal para la creación de aplicaciones Java.
- **Spring Data JPA**: Para la integración con bases de datos mediante JPA (Java Persistence API).
- **Spring Security**: Para la implementación de seguridad en la aplicación.
- **ModelMapper** (versión 3.0.0): Para mapear objetos entre diferentes capas de la aplicación.
- **JWT**: Para la autenticación y autorización mediante tokens JWT.
- **MySQL**: Base de datos relacional para almacenamiento de datos.
- **PostgreSQL**: Soporte adicional para bases de datos PostgreSQL.
- **Lombok**: Para la generación automática de código repetitivo como getters, setters, y constructores.
- **Swagger**: Para la documentación de la API REST.
- **Spring Boot Starter Validation**: Para validaciones en las entradas de la aplicación.

## Proyecto Angular

Este repositorio es solo la parte del **backend** para gestionar los contactos y usuarios. Si deseas ver cómo funciona con un frontend, tengo otro proyecto en Angular que interactúa con esta API para realizar operaciones CRUD sobre los contactos de manera gráfica.

Puedes encontrar ese proyecto en el siguiente [repositorio de Angular](https://github.com/Alejo224/contact-list-app).

## Endpoints Disponibles

### Contactos
| Método | Endpoint | Descripción |
|---------|----------|-------------|
| GET | `/api/contacts` | Obtiene la lista de todos los contactos. |
| GET | `/api/contacts/{id}` | Obtiene un contacto por su ID. |
| POST | `/api/contacts` | Crea un nuevo contacto. |
| PUT | `/api/contacts/{id}` | Actualiza la información de un contacto existente. |
| DELETE | `/api/contacts/{id}` | Elimina un contacto por su ID. |

### Usuarios
| Método | Endpoint | Descripción |
|---------|----------|-------------|
| GET | `/api/users` | Obtiene la lista de todos los usuarios. |
| GET | `/api/users/{id}` | Obtiene un usuario por su ID. |
| POST | `/api/users` | Crea un nuevo usuario. |
| PUT | `/api/users/{id}` | Actualiza la información de un usuario existente. |
| DELETE | `/api/users/{id}` | Elimina un usuario por su ID. |

## Requisitos

Para ejecutar este proyecto, necesitas tener instaladas las siguientes herramientas:

- **JDK 23** o superior.
- **Maven** para gestionar las dependencias.
- **MySQL** o **PostgreSQL** (dependiendo de la base de datos que prefieras usar).

## Instalación y Configuración

1. Clonar el repositorio:
   ```bash
   git clone git@github.com:Alejo224/contact-list-api.git
   cd contact-list-api
   ```

2. Configurar la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/contact_list_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Compilar y ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```
   
## Próximas Mejoras

- Implementación de autenticación y autorización con **Spring Security**.
- Integración con **JWT** para autenticación segura.
- Documentación de API con **Swagger**.

## Contribuciones

Si deseas contribuir, siéntete libre de hacer un fork del proyecto y enviar tus pull requests.

## Autor

[**Alejo224**](https://github.com/Alejo224)
