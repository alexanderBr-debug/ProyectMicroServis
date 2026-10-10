package com.yers7.sales_service.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.application.service.AprovedSaleService;

@Configuration 
public class BeanSaleAproved {
    
    @Bean 
    public AprovedSaleService aprovedSaleService(SaleRepositoryPort saleRepositoryPort){
        return new AprovedSaleService(saleRepositoryPort);
    }
}
