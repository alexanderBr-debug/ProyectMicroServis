# 🔐 Auth Microservice (`auth-service`)

![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-Event--Driven-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Liquibase](https://img.shields.io/badge/Liquibase-DB_Migration-29A8DF?style=for-the-badge&logo=liquibase&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Hexagonal_/_Clean-blueviolet?style=for-the-badge)

---

## 🚀 Aspectos Técnicos Destacados (Engineering Highlights)

Este microservicio fue diseñado siguiendo patrones de arquitectura empresarial y altos estándares de resiliencia, rendimiento y seguridad:

*   **Arquitectura Hexagonal (Ports & Adapters):** Desacoplamiento total entre la lógica pura de negocio (`domain`), los casos de uso (`application`) y los frameworks o persistencia (`infrastructure`).
*   **Java 21 Virtual Threads (Project Loom):** Manejo eficiente de concurrencia masiva e I/O bloqueante con consumo mínimo de recursos de hardware.
*   **Gestión Segura de Sesiones (JWT + Refresh Token Rotation):** Emisión de tokens de acceso de corta duración junto con tokens de refresco persistidos y rotados automáticamente para mitigar ataques de tipo *replay attack*.
*   **Rate Limiting por IP y Usuario:** Algoritmo *Token Bucket* implementado en la capa HTTP para prevenir ataques de fuerza bruta y denegación de servicio (DDoS).
*   **Control de Versiones de BD con Liquibase:** Evolución del esquema de base de datos automatizada, consistente e incremental mediante changelogs YAML/SQL.
*   **Arquitectura Orientada a Eventos (Kafka):** Publicación asíncrona de eventos de dominio (`UserRegisteredEvent`, `SecurityAlertEvent`) para auditoría y sincronización entre microservicios.
*   **Cifrado de Credenciales:** Encriptación robusta con `BCrypt` y sal dinámica para el almacenamiento seguro de contraseñas.
*   **Manejo Estandarizado de Excepciones (RFC 7807):** Respuestas de error estructuradas bajo el estándar *Problem Details for HTTP APIs*.

## 📐 Diagramas de Arquitectura y Flujos

### 1. Flujo de Autenticación y Emisión de Tokens (JWT + Refresh Token)

```mermaid
sequenceDiagram
    autonumber
    actor Client as Cliente
    participant API as AuthController
    participant RL as RateLimiterFilter
    participant UC as UseCases
    participant DB as PostgreSQL
    participant KAFKA as Apache Kafka

    Client->>API: POST /auth/login (Username, Password)
    API->>RL: Validar limite de peticiones (Token Bucket)
    alt Limite Excedido
        RL-->>Client: 429 Too Many Requests (RFC 7807)
    else Peticion Valida
        RL->>UC: Execute LoginUseCase
        UC->>DB: Buscar usuario & Verificar hash BCrypt
        alt Credenciales Invalidas
            DB-->>UC: Password mismatch / User Not Found
            UC-->>API: InvalidCredentialsException
            API-->>Client: 401 Unauthorized
        else Autenticacion Exitosa
            DB-->>UC: User Data
            UC->>UC: Generar Access Token (JWT) & Refresh Token
            UC->>DB: Guardar / Rotar Refresh Token
            UC->>KAFKA: Publicar UserLoggedInEvent (Async)
            UC-->>API: AuthResponse (Access Token + Refresh Token)
            API-->>Client: 200 OK + Payload con Tokens
        end
    end
```

---

### 2. Flujo de Integración de Eventos (Event-Driven con Kafka)

```mermaid
graph LR
    subgraph AuthMicroservice [Auth Microservice]
        A[AuthController] --> B[Application Layer]
        B --> C[UserEventPublisherPort]
        C --> D[KafkaUserEventPublisherAdapter]
    end

    subgraph EventBroker [Event Broker]
        D -->|Publish| E[Topic: user-security-events]
    end

    subgraph ConsumerMicroservices [Consumer Microservices]
        E -->|Consume| F[Notification Service]
        E -->|Consume| G[Audit & Logging Service]
        E -->|Consume| H[Analytics Service]
    end

    style E fill:#231F20,stroke:#fff,stroke-width:2px,color:#fff
    style AuthMicroservice fill:#1e1e1e,stroke:#6DB33F,stroke-width:2px,color:#fff
```