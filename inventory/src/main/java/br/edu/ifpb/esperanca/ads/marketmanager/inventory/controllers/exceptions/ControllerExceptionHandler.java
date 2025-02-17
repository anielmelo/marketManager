package br.edu.ifpb.esperanca.ads.marketmanager.inventory.controllers.exceptions;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.product.InsufficientQuantityException;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.product.InvalidProductQuantityException;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.product.ProductNotFoundException;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.replacement.ReplacementNotFoundException;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.supplier.SupplierAlreadyExistsException;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.supplier.SupplierNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardError> productNotFoundException(ProductNotFoundException e, HttpServletRequest request) {
        String error = "Entity record not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidProductQuantityException.class)
    public ResponseEntity<StandardError> invalidProductQuantityException(InvalidProductQuantityException e, HttpServletRequest request) {
        String error = "Invalid product quantity.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InsufficientQuantityException.class)
    public ResponseEntity<StandardError> insufficientQuantityException(InsufficientQuantityException e, HttpServletRequest request) {
        String error = "Insufficient quantity.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ReplacementNotFoundException.class)
    public ResponseEntity<StandardError> replacementNotFoundException(ReplacementNotFoundException e, HttpServletRequest request) {
        String error = "Entity record not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<StandardError> supplierNotFoundException(SupplierNotFoundException e, HttpServletRequest request) {
        String error = "Entity record not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SupplierAlreadyExistsException.class)
    public ResponseEntity<StandardError> supplierAlreadyExistsException(SupplierAlreadyExistsException e, HttpServletRequest request) {
        String error = "Can not register a supplier with this CNPJ.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> genericException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String error = "Param is required";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getBindingResult().getAllErrors().getFirst().getDefaultMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
