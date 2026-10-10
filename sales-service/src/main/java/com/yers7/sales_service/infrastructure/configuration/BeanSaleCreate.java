package com.yers7.sales_service.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yers7.sales_service.application.ports.out.ProductClientPort;
import com.yers7.sales_service.application.ports.out.SaleEventPublisherPort;
import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.application.service.CreateSaleService;

@Configuration 
public class BeanSaleCreate {
    
    @Bean 
    public CreateSaleService createSaleService(ProductClientPort productClientPort,
            SaleEventPublisherPort saleEventPublisherPort,
            SaleRepositoryPort saleRepositoryPort
    ){
        return new CreateSaleService(saleRepositoryPort, saleEventPublisherPort, productClientPort);
    }
}
