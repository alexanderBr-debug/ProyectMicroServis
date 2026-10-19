package com.yers7.sales_service.infrastructure.adapters.out.persistence;

import com.yers7.sales_service.application.ports.out.SaleRepositoryPort;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.domain.valueobject.SaleId;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.entity.SaleEntity;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.entity.SaleItemEntity;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.mapper.SalePersistenceMapper;
import com.yers7.sales_service.infrastructure.adapters.out.persistence.repository.SpringDataSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor 
public class SalePersistenceAdapter implements SaleRepositoryPort {

    private final SpringDataSaleRepository saleRepository;
    private final SalePersistenceMapper salePersistenceMapper;

    @Override
    public Sale save(Sale sale) {
        SaleEntity saleEntity = salePersistenceMapper.toEntity(sale);

        if(saleEntity.getItems() != null){
            List<SaleItemEntity> items = new ArrayList<>(saleEntity.getItems());
            saleEntity.getItems().clear();

            for (SaleItemEntity item : items){
                saleEntity.addItem(item);
            }
        }
        SaleEntity savedEntity = saleRepository.save(saleEntity);

        return salePersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Sale> findById(SaleId id) {
        return saleRepository.findById(id.value())
                .map(salePersistenceMapper::toDomain);
    }
}