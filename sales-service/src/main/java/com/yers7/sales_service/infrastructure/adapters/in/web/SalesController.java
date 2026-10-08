package com.yers7.sales_service.infrastructure.adapters.in.web;

import com.yers7.sales_service.application.ports.in.CreateSaleUseCase;
import com.yers7.sales_service.domain.model.Sale;
import com.yers7.sales_service.infrastructure.adapters.in.web.dto.CreateSaleRequest;
import com.yers7.sales_service.infrastructure.adapters.in.web.dto.SaleResponse;
import com.yers7.sales_service.infrastructure.adapters.in.web.mapper.SaleRestMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    private static final Logger log = LoggerFactory.getLogger(SalesController.class);

    private final CreateSaleUseCase createSaleUseCase;
    private final SaleRestMapper mapper;

    public SalesController(
            CreateSaleUseCase createSaleUseCase,
            SaleRestMapper mapper
    ) {
        this.createSaleUseCase = createSaleUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(@Valid @RequestBody CreateSaleRequest request) {
        log.info("Recibida petición HTTP para crear venta del cliente: {}", request.customerId());

       
        CreateSaleUseCase.CreateSaleCommand command = mapper.toCommand(request);

        Sale sale = createSaleUseCase.execute(command);

        SaleResponse response = mapper.toResponse(sale);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.saleId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

   
}