package com.yers7.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHnadler {
    
      
    @ExceptionHandler (PriceCannotBeNegativeException.class)
    public ResponseEntity<ErrorResponse> manejarPriceCannotBeNegative(PriceCannotBeNegativeException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "PRICE_BE_CANNOT_NEGATIVE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    @ExceptionHandler (ProductDuplicateException.class)
    public ResponseEntity<ErrorResponse> manejarProductDuplicate(ProductDuplicateException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "PRODUCT_DUPLICATE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    
    @ExceptionHandler (ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarProductNotFound( ProductNotFoundException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "PRODUCT_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

    @ExceptionHandler (RequiredProductNameException.class)
    public ResponseEntity<ErrorResponse> manejarRequieredProductName(RequiredProductNameException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "REQUIERED_PRODUCT_NAME");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    
    @ExceptionHandler (StrockCannotBeNegativeException.class)
    public ResponseEntity<ErrorResponse> manejarStockCannotBeNegative(StrockCannotBeNegativeException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "STOCK_CANNOT_BE_NEHATIVE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    @ExceptionHandler (StatusConflictException.class)
    public ResponseEntity<ErrorResponse> manejarStatusConflic(StatusConflictException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "CONFLIC_STATUS");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    
    }