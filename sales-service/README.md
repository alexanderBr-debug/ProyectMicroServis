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

sequenceDiagram
    autonumber
    actor Client as Cliente
    participant Auth as Auth / Payment Service
    participant Prod as Product Service
    participant Sales as Sales Service (Este Servicio)
    participant Notif as Notification Service
    participant Kafka as Apache Kafka

    Client->>Auth: 1. Iniciar sesión y procesar pago (JWT / Transacción)
    Auth->>Prod: 2. Consultar y verificar disponibilidad de stock (HTTP/Feign)
    Prod-->>Auth: Stock verificado y apartado OK
    Auth->>Sales: 3. POST /api/sales (Registrar orden de venta con Value Objects)
    Sales->>Sales: Validar dominio, persistir en PostgreSQL (Liquibase)
    Sales->>Kafka: 4. Publicar SaleCreatedEvent (Async)
    Sales-->>Client: 201 Created (Orden registrada con éxito)
    Kafka->>Notif: 5. Consumir evento de venta completada
    Notif->>Client: Enviar notificación / correo de confirmación

2. Flujo de Integración de Eventos (Event-Driven con Kafka)

graph LR
    subgraph SalesMicroservice [Sales Microservice]
        A[SalesController] --> B[CreateSaleUseCase / Service]
        B --> C[SaleEventPublisherPort]
        C --> D[SaleEventPublisherAdapter]
    end

    subgraph EventBroker [Event Broker]
        D -->|Publish| E[Topic: sales-events / sale-created]
    end

    subgraph ConsumerMicroservices [Consumer Microservices]
        E -->|Consume| F[Notification Service]
        E -->|Consume| G[Analytics / Audit Service]
        E -->|Consume| H[Shipping / Inventory Service]
    end

    style E fill:#231F20,stroke:#fff,stroke-width:2px,color:#fff
    style SalesMicroservice fill:#1e1e1e,stroke:#6DB33F,stroke-width:2px,color:#fff