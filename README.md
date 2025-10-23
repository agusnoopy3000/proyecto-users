# ☁️ SkyTech — Plataforma de Cloud Gaming (Microservicios)

> Backend desarrollado con Java Spring Boot para una plataforma de juegos en la Nube
---

## Descripción general
SkyTech es una plataforma de Cloud Gaming que permite a los usuarios jugar mediante streaming, delegando la ejecución en máquinas virtuales en la nube. Está diseñada con microservicios independientes que gestionan usuarios, suscripciones, catálogo de juegos, infraestructura y orquestación de streaming.


### Microservicios principales
- Usuarios  
  - Administra perfiles, credenciales, roles y metadatos de suscripción.
- Suscripción  
  - Controla el estado de suscripciones, fechas de vencimiento y facturación (si aplica).
- Juegos  
  - Catálogo: metadatos del juego, disponibilidad, requisitos técnicos y versiones.
- Infraestructura  
  - Gestión de máquinas virtuales / contenedores, reserva y liberación de recursos.
- Streaming (Orquestador)  
  - Valida permisos, solicita recursos a Infraestructura y genera endpoints para la sesión de streaming.

### Beneficios de esta arquitectura
- Separación clara de responsabilidades.
- Escalabilidad horizontal por servicio.
- Despliegue independiente y menor alcance de fallos.
- Reutilización y testabilidad más sencilla.

---

## Flujo de inicio de sesión / Orquestación de sesión
1. El usuario selecciona un juego en la UI.
2. Streaming solicita al servicio Usuarios verificar la suscripción activa.
3. Streaming solicita detalles del juego al servicio Juegos (requisitos y disponibilidad).
4. Streaming solicita una reserva de recursos a Infraestructura.
5. Infraestructura asigna una VM/instancia y responde con un endpoint (URL/IP + puerto).
6. Streaming devuelve el endpoint al cliente para iniciar la conexión remota.
7. La sesión se monitoriza y, al terminar, la Infraestructura libera los recursos.

---

## Diseño y buenas prácticas del backend

### Capas por microservicio
- Modelo — Entidades JPA / DTOs.
- Repositorio — Interfaces Spring Data JPA.
- Servicio — Lógica de negocio y transaccionalidad.
- Controlador — Endpoints RESTful (validaciones, mapeo DTO ↔ entidad).

### Estándares REST
- Recursos con nombres en plural: `/api/usuarios`, `/api/juegos`.
- Uso correcto de verbos HTTP:
  - GET para lectura, POST para creación, PUT/PATCH para actualización, DELETE para eliminación.
- Códigos HTTP adecuados: 200, 201, 204, 400, 401, 403, 404, 409, 500.
- Paginación, orden y filtros mediante query params.
- Uso de DTOs para entradas/salidas y evitar exponer entidades internas.

---

## Stack tecnológico
| Componente | Propósito |
|---|---|
| Spring Boot 3.x | Framework principal |
| Spring Web | Endpoints REST |
| Spring Data JPA | Persistencia |
| Spring HATEOAS | Enlaces navegables en respuestas REST |
| Maven | Gestión de dependencias y builds |
| Swagger / OpenAPI | Documentación de API |
| JUnit 5 + Mockito | Pruebas unitarias y mocks |
| MySQL (producción - EC2 u RDS) | Base de datos producción |
| H2 (local) | Base de datos en memoria para desarrollo/pruebas |

---

## Operaciones CRUD (ejemplo: microservicio Usuarios)

Ejemplos de endpoints:
| Método | Endpoint | Descripción |
|---|---:|---|
| GET | /api/usuarios | Listar todos los usuarios |
| GET | /api/usuarios/{id} | Obtener usuario por id |
| POST | /api/usuarios | Crear nuevo usuario |
| PUT | /api/usuarios/{id} | Actualizar usuario |
| DELETE | /api/usuarios/{id} | Eliminar usuario |


## Comunicación entre microservicios
- Se usa comunicación REST entre servicios (HTTP) para mantener el acoplamiento bajo.
- Cliente recomendado (en implementaciones actuales): RestTemplate (o WebClient para reactive / no bloqueante).
- Se deben aplicar timeouts, reintentos y circuit breakers (p. ej. Resilience4j) en entornos productivos.

### Ejemplo con RestTemplate (Infraestructura consultando Usuarios)
java
Usuarios usuario = restTemplate.getForObject(
    "http://localhost:8080/api/usuarios/" + idUsuario,
    Usuarios.class
);


### Pruebas unitarias
- @SpringBootTest (cuando se necesita contexto completo).
- @MockBean para simular dependencias (repositorios, clientes REST).
- JUnit 5 + Mockito para validar lógica de servicio, manejo de excepciones y casos límite.

### Pruebas de controlador (API)
- @WebMvcTest para testear controladores aislados.
- Validar códigos de respuesta, formato JSON y contenido.
- Usar MockMvc para construir peticiones HTTP simuladas.

### Pruebas de integración / Postman
- Colecciones de Postman para validar flujos CRUD y orquestación de sesión.
- Validaciones en Postman / Newman: códigos HTTP esperados (200, 201, 204, 404).
- En integration tests se pueden arrancar contenedores con Testcontainers (MySQL) para pruebas realistas.

---

## HATEOAS — Respuestas enlazadas
Se usa Spring HATEOAS para devolver EntityModel y enlaces navegables. Beneficios:
- El cliente descubre acciones disponibles.
- Menor acoplamiento al formato exacto de rutas.

Ejemplo de respuesta (infraestructura):
json
{
  "id": 1,
  "estado": "ACTIVO",
  "_links": {
    "verificar": { "href": "/api/infraestructura/1/verificar" },
    "cancelar": { "href": "/api/infraestructura/1/cancelar" }
  }
}


---

## Conclusión
SkyTech se construyó con foco en modularidad, escalabilidad y mantenibilidad aplicando buenas prácticas (arquitectura por capas, tests, HATEOAS y CI/CD). Su diseño por microservicios facilita la evolución del producto y la incorporación de nuevas capacidades con mínimo impacto a servicios existentes.

> "La visión de SkyTech no solo es ofrecer juegos, sino una experiencia en la nube que combine tecnología, rendimiento y escalabilidad."
