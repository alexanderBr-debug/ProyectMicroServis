package com.yers7.sales_service.infrastructure.adapters.out.persistence;

import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.valueobject.SaleId;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.entity.SaleEntity;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.mapper.SalePersistenceMapper;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.repository.SpringDataSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SalePersistenceAdapter implements SaleRepositoryPort {

    private final SpringDataSaleRepository saleRepository;
    private final SalePersistenceMapper salePersistenceMapper;

    @Override
    public Sale save(Sale sale) {
        SaleEntity entity = salePersistenceMapper.toEntity(sale);
        SaleEntity savedEntity = saleRepository.save(entity);
        return salePersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Sale> findById(SaleId id) {
        return saleRepository.findById(id.value())
                .map(salePersistenceMapper::toDomain);
    }
}