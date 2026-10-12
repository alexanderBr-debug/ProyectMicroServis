package com.yers7.product_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yers7.product_service.objectValo.ProductPrice;
import com.yers7.product_service.objectValo.ProductStock;


import java.time.Instant;


@Entity
@Table(name = "products")
@Data 
@Builder
@AllArgsConstructor 
@NoArgsConstructor 
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity {

    

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private ProductPrice price;

    @Column(name = "stock", nullable = false)
    private ProductStock stock; // Stock físico disponible

    @Column(name = "reserved_stock", nullable = false)
    private Integer reservedStock; // Stock apartado temporalmente (Saga)

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private ProductStatus status; 

    @Version
    @Column(name = "version", nullable = false)
    private Long version; 

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}


