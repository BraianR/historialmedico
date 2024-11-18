# API de Gestión del Historial Médico

## Introducción
Este proyecto desarrolla un sistema backend para gestionar eficientemente el historial médico de pacientes, facilitando el acceso rápido a información vital y mejorando la coordinación de cuidados médicos. El sistema proporciona servicios RESTful para manejar operaciones relacionadas con el registro de pacientes y la gestión del historial médico, utilizando bases de datos NoSQL para el almacenamiento de datos.

## Tecnologías Utilizadas
- Lenguaje de Programación: Java
- Framework: Spring Boot
- Base de Datos: MongoDB (Principal), Redis (Caché)
- Otros: Docker, Maven, JMeter para pruebas de carga, Postman, Studio3T para validación de datos, Git

## Diseño de la Base de Datos
Optamos por MongoDB debido a su eficiencia en manejar documentos JSON, que se integran perfectamente con nuestras API REST. MongoDB facilita la inclusión y gestión de registros médicos complejos directamente en los perfiles de pacientes, mejorando el rendimiento y simplificando las actualizaciones. Cada documento en la colección `Pacientes` incluye un array de registros médicos embebidos, optimizando las consultas.

### Redis
Incorporamos Redis como solución de caché para mejorar la eficiencia y el rendimiento de las consultas, ofreciendo tiempos de acceso rápidos que son cruciales para la recuperación de datos frecuentes y reduciendo la carga sobre MongoDB.

## Implementación de la Caché
Redis está operando efectivamente como caché, almacenando y recuperando los datos de pacientes desde la caché, lo que minimiza la necesidad de accesos repetidos a la base de datos principal.

## Dockerización del Sistema y Eficiencia de Recursos
El sistema, incluyendo Redis y MongoDB, está completamente dockerizado, lo que garantiza la coherencia entre los diferentes entornos de desarrollo y prueba. El uso de CPU y memoria por parte de Redis es notablemente bajo, demostrando su eficiencia.

## Instalación y Configuración
### Requisitos
- Java JDK 21
- Docker
- MongoDB
- Redis

### Pasos para Ejecutar el Proyecto

- git clone https://github.com/BraianR/historialmedico
- abrir directorio del proyecto
- ./gradlew build
- docker-compose up --build


### Documentación de la API
- URL Base
http://localhost:8080/api/pacientes

Endpoints:

- Agregar Paciente

URL: /agregar
Método: POST
Body: { "ci": "12345", "nombre": "Nombre","apellido": "Apellido","fechaNacimiento": "1990-01-01", "sexo": "M"}
Respuesta de Éxito: Código: 201
Respuesta de Error: Código: 401, Contenido: { error: "El paciente ya existe" }

Agregar Registro Médico
URL: /registroMedico/{ci}
Método: POST
Body: { "fecha": "2024-11-05", "diagnostico": "Resfrio1", "medico": "Romero", "institucion":
"Tecnologo", "tipoRegistroMedico": "CONSULTA", "descripcion": "Tiene Resfrio1", "medicacion":
"Paracetamol1" }
Respuesta de Éxito: Código: 201
Respuesta de Error: Código: 402, Contenido: { error: "No existe un paciente con la cedula aportada como parametro" }

Consultar Historial Médico
URL: /historialMedico/{ci}
Request Params:
size (Integer, opcional): Número de registros por página.
page (Integer, opcional): Número de página a consultar.
Método: GET
Respuesta de Éxito: Código: 200
Respuesta de Error: Código: 402, Contenido: { error: "No existe un paciente con la cedula aportada como parametro" }

Consultar Registros por Criterio
URL: /historialMedico/{ci}
Request Params:
tipoRegistroMedico (String, opcional): Tipo de registro (e.g., CONSULTA).
institucion (String, opcional): Nombre de la institución.
diagnostico (String, opcional): Diagnóstico asociado.
medico (String, opcional): Médico tratante.
Método: GET
Respuesta de Éxito: Código: 200


Información de Contacto
Desarrollador: Braian Romero
Correo electrónico: braianr1318@gmail.com
Desarrollador: Facundo Navarro
Correo electrónico: facundonicolasnavarro@gmail.com


Documentación Adicional
Para el desarrollo de este proyecto, se han utilizado diversas herramientas y tecnologías cuyas documentaciones y recursos han sido esenciales
para la implementación efectiva de las funcionalidades. A continuación, se incluyen enlaces a algunos de estos recursos

MongoDB
    Documentación oficial de MongoDB - https://docs.mongodb.com
    Guía de inicio rápido de MongoDB - https://docs.mongodb.com/manual/tutorial/getting-started
Redis
    Documentación oficial de Redis  - https://redis.io/docs/latest/
Docker
    Documentación oficial de Docker - https://docs.docker.com/manuals/
Spring Boot
    Documentación oficial de Spring Boot - https://docs.spring.io/spring-boot/docs/current/reference/html/
    Construyendo API REST con Spring Boot - https://spring.io/guides/tutorials/rest
JMeter
    Documentación oficial de Apache JMeter - https://jmeter.apache.org/usermanual/index.html
    Guías para pruebas de carga con JMeter - https://jmeter.apache.org/usermanual/best-practices.html
