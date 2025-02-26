package br.edu.ifpb.esperanca.ads.marketmanager.sale.controllers.exceptions;


import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale.SaleNotFoundException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct.InvalidProductIdException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct.ResourceNotFoundException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct.SaleProductNotFoundException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale.InvalidSaleOperationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<StandardError> saleNotFoundException(SaleNotFoundException e, HttpServletRequest request) {
        String error = "Sale record not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SaleProductNotFoundException.class)
    public ResponseEntity<StandardError> saleProductNotFoundException(SaleProductNotFoundException e, HttpServletRequest request) {
        String error = "Sale product record not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidSaleOperationException.class)
    public ResponseEntity<StandardError> invalidSaleOperationException(InvalidSaleOperationException e, HttpServletRequest request) {
        String error = "Invalid sale operation.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "Invalid request parameters.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getBindingResult().getAllErrors().getFirst().getDefaultMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<StandardError> handleInvalidProductId(InvalidProductIdException e, HttpServletRequest request) {
        String error = "Invalid Product ID.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }



}

