Markdown
# 🛒 Sales Microservice (`sales-service`)

![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Cloud OpenFeign](https://img.shields.io/badge/Spring_Cloud-OpenFeign-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-Event--Driven-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Liquibase](https://img.shields.io/badge/Liquibase-DB_Migration-29A8DF?style=for-the-badge&logo=liquibase&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Hexagonal_/_DDD-blueviolet?style=for-the-badge)

---

## 🚀 Aspectos Técnicos Destacados (Engineering Highlights)

Este microservicio fue diseñado siguiendo patrones de arquitectura empresarial, separación estricta de responsabilidades y un enfoque guiado por el dominio:

- **Arquitectura Hexagonal (Ports & Adapters) & DDD:** Desacoplamiento total entre la lógica pura de negocio (`domain`), los casos de uso (`application`) y los frameworks externos o persistencia (`infrastructure`).
- **Objetos de Valor Inmutables (*Value Objects*):** Encapsulamiento estricto de reglas de negocio en clases como `Money`, `CustomerId` y `ProductId`, garantizando validaciones automáticas al momento de su instanciación (*Fail-Fast*).
- **Comunicación Sincrónica por OpenFeign:** Clientes HTTP declarativos para consultar la disponibilidad y apartar stock de forma segura con el microservicio de productos.
- **Arquitectura Orientada a Eventos (Kafka):** Publicación asíncrona de eventos de dominio (`SaleCreatedEvent`, `SaleCancelledEvent`) para mantener la consistencia eventual con el resto de servicios del ecosistema.
- **Control de Versiones de BD con Liquibase:** Evolución del esquema de base de datos automatizada, incremental y segura mediante changelogs basados en YAML.
- **Manejo Estandarizado de Excepciones de Negocio:** Centralización de errores de dominio (montos negativos, divisas inválidas, stock no disponible) para respuestas HTTP claras y predecibles.

---