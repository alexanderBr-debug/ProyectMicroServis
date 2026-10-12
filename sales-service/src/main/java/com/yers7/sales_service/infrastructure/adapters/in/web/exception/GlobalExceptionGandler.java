package com.yers7.sales_service.infrastructure.adapters.in.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yers7.sales_service.domain.exception.AmountCannotBeNegativeException;
import com.yers7.sales_service.domain.exception.CannotAddUpDifferentCurrenciesException;
import com.yers7.sales_service.domain.exception.CustomerIsInvalidException;
import com.yers7.sales_service.domain.exception.DomainException;
import com.yers7.sales_service.domain.exception.RequiredCurrencyException;
import com.yers7.sales_service.domain.exception.SaleIdNotNullException;
import com.yers7.sales_service.domain.exception.SaleNotFoundException;

@RestControllerAdvice 
public class GlobalExceptionGandler {
    
    @ExceptionHandler (AmountCannotBeNegativeException.class)
    public ResponseEntity<ErrorResponse> manejarAmountCannotBeNegative(AmountCannotBeNegativeException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "AMOUNT_BE_CANNOT_NEGATIVE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    @ExceptionHandler (CannotAddUpDifferentCurrenciesException.class)
    public ResponseEntity<ErrorResponse> manejarCannotAddUpDifferentCurrenci(CannotAddUpDifferentCurrenciesException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "CANNOT_ADD_UP_DIFFERENT_CURRENCY");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    
    @ExceptionHandler (CustomerIsInvalidException.class)
    public ResponseEntity<ErrorResponse> manejarCustomerIdInvalid(CustomerIsInvalidException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "CUSTOMER_IS_INVALID");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
        }

    @ExceptionHandler (DomainException.class)
    public ResponseEntity<ErrorResponse> manejarDomainException(DomainException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "DOMAIN_EXCEPTION");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    
    @ExceptionHandler (RequiredCurrencyException.class)
    public ResponseEntity<ErrorResponse> manejarRequieredCurrency(RequiredCurrencyException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "REQUIERED_CURRENCY");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    
    @ExceptionHandler (SaleIdNotNullException.class)
    public ResponseEntity<ErrorResponse> manejarSaleIdNotNull(SaleIdNotNullException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "SALE_ID_NOT_NULL");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    @ExceptionHandler (SaleNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarSaleNotFound(SaleNotFoundException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(),"SALE_NOT_FOUND" );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    

    
    }

