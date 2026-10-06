```text
auth_service/
├── src/main/java/com/yers7/auth_service/
│   │
│   ├── application/                           # CAPA DE APLICACIÓN (Casos de uso y orquestación)
│   │   ├── ports/                             # Fronteras del sistema
│   │   │   ├── in/                            # Puertos de Entrada (Casos de Uso)
│   │   │   │   ├── LoginUseCase.java
│   │   │   │   ├── RefreshTokenUseCase.java
│   │   │   │   ├── RegisterUseCase.java
│   │   │   │   └── ValidateTokenUseCase.java
│   │   │   └── out/                           # Puertos de Salida (Interfaces requeridas)
│   │   │       ├── PasswordEncoderPort.java
│   │   │       ├── RefreshTokenRepositoryPort.java
│   │   │       ├── TokenProviderPort.java
│   │   │       ├── UserEventPublisherPort.java
│   │   │       └── UserRepositoryPort.java
│   │   └── service/                           # Implementación de los Casos de Uso
│   │       ├── AuthService.java
│   │       └── TokenService.java
│   │
│   ├── domain/                                # CAPA DE DOMINIO (Reglas puras del negocio)
│   │   ├── exception/                         # Excepciones de negocio propias
│   │   │   ├── InvalidCredentialsException.java
│   │   │   ├── RateLimitExceededException.java
│   │   │   ├── TokenExpiredException.java
│   │   │   ├── TokenNotFoundException.java
│   │   │   ├── TokenRevokedException.java
│   │   │   └── UserAlreadyExistsException.java
│   │   └── model/                             # Objetos de Dominio y Objetos de Valor
│   │       ├── RefreshToken.java
│   │       ├── Role.java
│   │       ├── TokenPair.java
│   │       └── User.java
│   │
│   └── infrastructure/                        # CAPA DE INFRAESTRUCTURA (Adaptadores y Framework)
│       ├── adapters/
│       │   ├── in/                            # ADAPTADORES DE ENTRADA (Driving Adapters)
│       │   │   └── web/                       # Controladores REST HTTP
│       │   │       ├── dto/                   # DTOs de Request y Response HTTP
│       │   │       │   ├── AuthResponse.java
│       │   │       │   ├── LoginRequest.java
│       │   │       │   ├── RefreshTokenRequest.java
│       │   │       │   └── RegisterRequest.java
│       │   │       ├── exception/             # Manejo global de excepciones web
│       │   │       │   ├── ErrorResponse.java
│       │   │       │   └── GlobalExceptionHandler.java
│       │   │       ├── filter/                # Filtros de seguridad HTTP
│       │   │       │   ├── JwtAuthenticationFilter.java
│       │   │       │   └── RateLimitFilter.java
│       │   │       ├── mapper/                # Mapeo Dominio <-> DTOs Web
│       │   │       │   └── AuthWebMapper.java
│       │   │       └── AuthController.java
│       │   │
│       │   └── out/                           # ADAPTADORES DE SALIDA (Driven Adapters)
│       │       ├── messaging/                 # Publicadores de eventos a Kafka
│       │       │   ├── KafkaUserEventPublisherAdapter.java
│       │       │   ├── SecurityEvent.java
│       │       │   └── UserRegisterEvent.java
│       │       ├── persistence/               # Persistencia PostgreSQL / JPA
│       │       │   ├── entity/                # Entidades JPA (@Entity)
│       │       │   │   ├── RefreshTokenEntity.java
│       │       │   │   └── UserEntity.java
│       │       │   ├── mapper/                # Mapeo Dominio <-> Entidades JPA
│       │       │   │   ├── RefreshTokenPersistenceMapper.java
│       │       │   │   └── UserPersistenceMapper.java
│       │       │   ├── repository/            # Interfaces de Spring Data JPA
│       │       │   │   ├── SpringDataRefreshTokenRepository.java
│       │       │   │   └── SpringDataUserRepository.java
│       │       │   ├── RefreshTokenPersistenceAdapter.java
│       │       │   └── UserPersistenceAdapter.java
│       │       └── security/                  # Implementación de JWT y Encriptación
│       │           ├── jwt/
│       │           │   ├── JwtProperties.java
│       │           │   └── JwtTokenProviderAdapter.java
│       │           └── password/
│       │               ├── BCryptPasswordEncoderAdapter.java
│       │               └── BeanPassword.java
│       │
│       └── config/                            # Configuración e inyección de Spring Boot
│           ├── ApplicationBeanConfig.java
│           ├── RateLimitConfig.java
│           ├── SecurityConfig.java
│           └── AuthServiceApplication.java
│
└── src/main/resources/                       # RECURSOS Y MIGRACIONES
    ├── db/changelog/                          # Control de versiones de BD (Liquibase)
    │   ├── changes/
    │   │   └── v001-create-auth-tables.yaml
    │   └── db.changelog-master.yaml
    └── application.yaml                       # Propiedades del microservicio