# Estructura Completa del Proyecto - Sales Service

A continuación se detalla la jerarquía exacta de directorios, subdirectorios y archivos que componen el microservicio, organizada bajo los principios de Arquitectura Hexagonal y Domain-Driven Design (DDD):

```text
sales-service/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── yers7/
│   │   │           └── sales_service/
│   │   │               │
│   │   │               ├── application/
│   │   │               │   ├── ports/
│   │   │               │   │   ├── in/
│   │   │               │   │   │   ├── ApproveSaleUseCase.java
│   │   │               │   │   │   ├── CancelSaleUseCase.java
│   │   │               │   │   │   └── CreateSaleUseCase.java
│   │   │               │   │   └── out/
│   │   │               │   │       ├── ProductClientPort.java
│   │   │               │   │       ├── SaleEventPublisherPort.java
│   │   │               │   │       └── SaleRepositoryPort.java
│   │   │               │   └── service/
│   │   │               │       ├── ApprovedSaleService.java
│   │   │               │       ├── CancelSaleService.java
│   │   │               │       └── CreateSaleService.java
│   │   │               │
│   │   │               ├── domain/
│   │   │               │   ├── enums/
│   │   │               │   │   └── SaleStatus.java
│   │   │               │   ├── exception/
│   │   │               │   │   ├── AmountCannotBeNegativeException.java
│   │   │               │   │   ├── CannotAddUpDifferentCurrenciesException.java
│   │   │               │   │   ├── CustomerInvalidException.java
│   │   │               │   │   ├── DomainException.java
│   │   │               │   │   ├── RequiredCurrencyException.java
│   │   │               │   │   ├── SaleIdNotFoundException.java
│   │   │               │   │   └── SaleNotFoundException.java
│   │   │               │   ├── model/
│   │   │               │   │   ├── Sale.java
│   │   │               │   │   └── SaleItem.java
│   │   │               │   └── valueobject/
│   │   │               │       ├── CustomerId.java
│   │   │               │       ├── Money.java
│   │   │               │       └── ProductId.java
│   │   │               │
│   │   │               ├── infrastructure/
│   │   │               │   ├── adapters/
│   │   │               │   │   ├── client/
│   │   │               │   │   │   ├── ProductClientAdapter.java
│   │   │               │   │   │   └── ProductResponse.java
│   │   │               │   │   ├── messaging/
│   │   │               │   │   │   └── event/
│   │   │               │   │       ├── PaymentFailedEvent.java
│   │   │               │   │       └── StockDeductedEvent.java
│   │   │               │   │   ├── persistence/
│   │   │               │   │   │   ├── entity/
│   │   │               │   │   │   │   ├── SaleEntity.java
│   │   │               │   │   │   │   └── SaleItemEntity.java
│   │   │               │   │   │   ├── mapper/
│   │   │               │   │   │   │   ├── SaleItemPersistenceMapper.java
│   │   │               │   │   │   │   └── SalePersistenceMapper.java
│   │   │               │   │   │   ├── repository/
│   │   │               │   │   │   │   ├── SalePersistenceAdapter.java
│   │   │               │   │   │   │   └── SpringDataSaleRepository.java
│   │   │               │   │   │   └── event/
│   │   │               │   │       └── SaleEventPublisherAdapter.java
│   │   │               │   │   └── web/
│   │   │               │   │       ├── SalesController.java
│   │   │               │   │       ├── dto/
│   │   │               │   │       │   └── CreateSaleRequest.java
│   │   │               │   │       └── mapper/
│   │   │               │   │           └── SaleRestMapper.java
│   │   │               │   └── configuration/
│   │   │               │
│   │   │               └── SalesServiceApplication.java
│   │   │
│   │   └── resources/
│   │       ├── db/
│   │       │   └── changelog/
│   │       │       ├── db.changelog-master.yaml
│   │       │       └── v0001-create-sales-tables.yaml
│   │       └── application.yml
│
└── pom.xml