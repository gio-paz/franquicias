# Franquicias API

API REST reactiva para la gestión de franquicias, sucursales y productos. Desarrollada como prueba técnica, implementando programación reactiva con Spring WebFlux y arquitectura limpia (Clean Architecture).

## Tecnologías

| Tecnología | Versión |
|-----------|---------|
| Java | 21 |
| Spring Boot | 3.5.12 |
| Spring WebFlux | Reactivo (Netty) |
| Spring Data R2DBC | Reactivo |
| MySQL | 8.0 |
| r2dbc-mysql (asyncer) | 1.3.0 |
| Lombok | — |
| JUnit 5 + Mockito | — |
| StepVerifier (Reactor Test) | — |
| Maven | — |

---

## Arquitectura

El proyecto aplica **Clean Architecture**, separando responsabilidades en capas independientes:

```
src/main/java/com/Giovanny/franquicias/
├── domain/
│   ├── model/               → Entidades del dominio (Franquicia, Sucursal, Producto)
│   └── port/
│       ├── in/              → Interfaces de casos de uso (FranquiciaUseCase, etc.)
│       └── out/             → Interfaces de repositorios (FranquiciaRepositoryPort, etc.)
├── application/
│   └── usecase/             → Implementación de la lógica de negocio
└── infrastructure/
    └── adapter/
        ├── in/web/          → Controllers REST (HTTP)
        └── out/persistence/ → Repositorios R2DBC + Adaptadores
```

**Principios aplicados:**
- Las capas internas (`domain`, `application`) no dependen de ningún framework externo
- Los controllers y repositorios son detalles de infraestructura
- Las dependencias siempre apuntan hacia adentro

---

## Requisitos previos

- Java 21
- Maven (o usar el wrapper `mvnw` incluido)
- MySQL 8.0 corriendo localmente

---

## Configuración de base de datos

Ejecuta estos scripts en MySQL:

```sql
CREATE DATABASE IF NOT EXISTS franquisias;

USE franquisias;

CREATE TABLE franquicia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE sucursal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    franquicia_id BIGINT NOT NULL,
    FOREIGN KEY (franquicia_id) REFERENCES franquicia(id)
);

CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    sucursal_id BIGINT NOT NULL,
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);
```

---

## Variables de configuración

Edita `src/main/resources/application.properties` si tus credenciales son distintas:

```properties
spring.r2dbc.url=r2dbc:mysql://localhost:3306/franquisias?sslMode=DISABLED&allowPublicKeyRetrieval=true
spring.r2dbc.username=root
spring.r2dbc.password=123456
```

---

## Ejecución local

```bash
# Clonar el repositorio
git clone https://github.com/gio-paz/franquicias.git
cd franquicias

# Compilar
./mvnw compile

# Ejecutar
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

---

## Ejecución de pruebas unitarias

```bash
./mvnw test
```

**Resultado esperado:**
```
Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Cobertura de tests

| Clase testeada | Tests | Descripción |
|---------------|-------|-------------|
| `FranquiciaUseCaseImpl` | 4 | Agregar, actualizar nombre, error si no existe, listar todas |
| `SucursalUseCaseImpl` | 3 | Agregar con franquiciaId, actualizar nombre, error si no existe |
| `ProductoUseCaseImpl` | 4 | Agregar con sucursalId, actualizar stock, actualizar nombre, producto con más stock |

---

## Endpoints de la API

### Franquicias

| Método | URL | Descripción |
|--------|-----|-------------|
| `POST` | `/api/franquicias` | Agregar una nueva franquicia |
| `PUT` | `/api/franquicias/{id}/nombre?nuevoNombre=X` | Actualizar nombre de franquicia |
| `GET` | `/api/franquicias` | Listar todas las franquicias |

### Sucursales

| Método | URL | Descripción |
|--------|-----|-------------|
| `POST` | `/api/sucursales/franquicia/{franquiciaId}` | Agregar sucursal a una franquicia |
| `PUT` | `/api/sucursales/{id}/nombre?nuevoNombre=X` | Actualizar nombre de sucursal |

### Productos

| Método | URL | Descripción |
|--------|-----|-------------|
| `POST` | `/api/productos/sucursal/{sucursalId}` | Agregar producto a una sucursal |
| `PUT` | `/api/productos/{id}/nombre?nuevoNombre=X` | Actualizar nombre de producto |
| `PUT` | `/api/productos/{id}/stock?nuevoStock=X` | Actualizar stock de producto |
| `DELETE` | `/api/productos/{id}` | Eliminar producto de una sucursal |
| `GET` | `/api/productos/franquicia/{franquiciaId}/mayor-stock` | Producto con más stock por sucursal de una franquicia |

---

## Ejemplos de uso (Postman / curl)

### Crear franquicia
```http
POST http://localhost:8080/api/franquicias
Content-Type: application/json

{
  "nombre": "McDonald's"
}
```

### Agregar sucursal
```http
POST http://localhost:8080/api/sucursales/franquicia/1
Content-Type: application/json

{
  "nombre": "Sucursal Bogotá"
}
```

### Agregar producto
```http
POST http://localhost:8080/api/productos/sucursal/1
Content-Type: application/json

{
  "nombre": "Big Mac",
  "stock": 100
}
```

### Consultar producto con más stock por franquicia
```http
GET http://localhost:8080/api/productos/franquicia/1/mayor-stock
```

**Respuesta:**
```json
[
  {
    "id": 2,
    "nombre": "Big Mac",
    "stock": 100,
    "sucursalId": 1
  }
]
```

---

## Programación reactiva

La API es completamente **no bloqueante**:
- Todos los endpoints retornan `Mono<T>` (un elemento) o `Flux<T>` (colección)
- El servidor usa **Netty** en lugar de Tomcat
- La comunicación con la base de datos usa **R2DBC** (reactivo)
- Los tests usan **StepVerifier** para verificar flujos reactivos

---

## Estructura del proyecto

```
franquicias/
├── src/
│   ├── main/
│   │   ├── java/com/Giovanny/franquicias/
│   │   │   ├── domain/
│   │   │   ├── application/
│   │   │   ├── infrastructure/
│   │   │   └── FranquiciasApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/Giovanny/franquicias/
│           ├── FranquiciasApplicationTests.java
│           └── service/
│               ├── FranquiciaServiceTest.java
│               ├── SucursalServiceTest.java
│               └── ProductoServiceTest.java
├── pom.xml
└── README.md
```

---

## Repositorio

[https://github.com/gio-paz/franquicias](https://github.com/gio-paz/franquicias)
