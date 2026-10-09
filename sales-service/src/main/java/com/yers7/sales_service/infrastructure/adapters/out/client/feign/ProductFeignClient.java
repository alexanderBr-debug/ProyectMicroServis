package com.yers7.sales_service.infrastructure.adapters.out.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yers7.sales_service.infrastructure.adapters.out.client.dto.ProductResponseDto;

// El nombre "product-service" se usaría si tuvieras un Service Discovery (como Eureka).
// Como estamos configurando manual, pasamos la URL desde el application.yml
@FeignClient (name = "product-service", url = "${application.clients.product.url}")
public interface ProductFeignClient {

    @GetMapping ("/api/v1/products/{productId}")
    ProductResponseDto getProductById(@PathVariable ("productId") String productId);
}