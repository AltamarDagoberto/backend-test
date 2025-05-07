# Backend Microservices Project
Este proyecto consta de dos microservicios: Product Service y Inventory Service. A continuación se detallan las instrucciones de instalación, ejecución, arquitectura y decisiones técnicas clave, así como un diagrama de interacción entre servicios.

# 1. Instrucciones de Instalación y Ejecución
Prerrequisitos
Asegúrate de tener las siguientes herramientas instaladas:

Java 17 o superior.

Maven 3.8.1 o superior.

Docker (opcional, si se requiere para la ejecución de contenedores).

IDE de tu preferencia (como Visual Studio Code, IntelliJ IDEA, etc.).

Clonar el Repositorio
- git clone https://github.com/AltamarDagoberto/backend-test.git


# Ejecutar los Servicios Localmente
#### 1) Product Service:
Navega al directorio del servicio de productos:
- cd product-service

Ejecuta el microservicio utilizando Maven:
- mvn clean spring-boot:run

# 2 Inventory Service:

Navega al directorio del servicio de inventario:
- cd inventory-service

Ejecuta el microservicio utilizando Maven:
- mvn clean spring-boot:run

# 3 Ejecutar los Servicios en Docker
#### 1) Construir la imagen Docker para product-service:
- docker build -t product-service .
- docker run -p 8080:8080 product-service

#### 2) Construir la imagen Docker para inventory-service:
- docker build -t inventory-service .
- docker run -p 8081:8081 inventory-service



# Descripción Breve de la Arquitectura
Este proyecto sigue la arquitectura de Microservicios. Los dos servicios principales son:

- Product Service: Gestiona la información de productos, incluyendo operaciones CRUD (crear, leer, actualizar, eliminar).

- Inventory Service: Maneja la disponibilidad de productos en inventarios, interactuando con el servicio de productos para obtener información sobre los productos disponibles.

# Comunicación entre Servicios
Los servicios se comunican mediante REST APIs. El servicio de inventario hace llamadas HTTP al servicio de productos para obtener información adicional sobre los productos cuando es necesario.


# Decisiones Técnicas y Justificaciones
Elección de Tecnología
- Spring Boot: Usé Spring Boot para ambos servicios debido a su facilidad de configuración y rapidez para crear aplicaciones Java autónomas.

- Maven: Para la gestión de dependencias y construcción de los proyectos, decidimos utilizar Maven debido a su amplia adopción y robustez en proyectos Java.

- Base de Datos H2: Se ha elegido H2 como base de datos en memoria para simplificar la implementación en el entorno de desarrollo y pruebas. En un entorno de producción, se podría cambiar a una base de datos más robusta como PostgreSQL o MySQL.

- Mockito: Para las pruebas unitarias y de integración, utilizamos Mockito para simular dependencias externas como clientes HTTP.

- Docker: Se implementaron contenedores Docker para facilitar la ejecución en diferentes entornos y simplificar la orquestación.


# Justificación de la Arquitectura de Microservicios
- Optamos por una arquitectura de microservicios para promover la escalabilidad, la flexibilidad y la modularidad. Al dividir la funcionalidad en servicios independientes, se facilita el mantenimiento, la actualización y la escalabilidad horizontal.


# Diagrama de Interacción entre Servicios
![image](https://github.com/user-attachments/assets/9f8540dc-971b-454c-a631-f5966353082c)


- El Inventory Service realiza peticiones HTTP al Product Service para obtener información sobre los productos.

- Ambas aplicaciones interactúan con sus respectivas bases de datos (en este caso, H2).


# Conclusión
- Este proyecto demuestra el uso de microservicios en una arquitectura sencilla, permitiendo la integración de dos servicios independientes que se comunican entre sí mediante APIs REST. Las tecnologías utilizadas proporcionan flexibilidad y escalabilidad para proyectos más grandes

# EVIDENCIAS
![11](https://github.com/user-attachments/assets/8524120a-334e-4241-b60f-844d26f67b13)
![4](https://github.com/user-attachments/assets/3929e220-c88f-47ba-9f1b-0cc2a0f5e358)
![3](https://github.com/user-attachments/assets/f6d901ef-a1ab-40dc-a930-ff81460aabed)
![2](https://github.com/user-attachments/assets/f9908be5-e1b5-4613-9908-9dd985e80370)
![1](https://github.com/user-attachments/assets/7d2b6f9a-e21c-42e6-a27c-95d9ffa16310)
![44](https://github.com/user-attachments/assets/fd733eb5-c797-4fd9-8b4a-509c94102365)
![33](https://github.com/user-attachments/assets/16333657-262f-48c7-a3a5-5797b0a5e1c1)
![22](https://github.com/user-attachments/assets/a17953d6-76a1-48c1-a9b7-f816b0b76dd8)

