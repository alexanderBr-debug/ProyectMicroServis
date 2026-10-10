package com.yers7.sales_service.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yers7.sales_service.application.ports.out.SaleEventPublisherPort;
import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.application.service.CancelSaleService;

@Configuration 
public class BeanSaleCancel {
    
    @Bean 
    public CancelSaleService cancelSaleService(
        SaleRepositoryPort saleRepositoryPort,
        SaleEventPublisherPort saleEventPublisherPort
    ){
        return  new CancelSaleService(saleRepositoryPort, saleEventPublisherPort);
    }
}
