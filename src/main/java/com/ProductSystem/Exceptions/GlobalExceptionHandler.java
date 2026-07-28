package com.ProductSystem.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleProductNotFound(ProductNotFoundException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new  ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse>handleInvalidInput(InvalidInputException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleUserNotFound(UserNotFoundException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse>handleProductAlreadyExists(ProductAlreadyExistsException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse>handleInsufficientStock(InsufficientStockException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse>handleUserAlreadyExists(UserAlreadyExistsException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse>handleCategoryAlreadyExists(CategoryAlreadyExistsException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleCategoryNotFound(CategoryNotFoundException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleAddressNotFound(AddressNotFoundException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleOrderNotFound(OrderNotFoundException ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }



    //for validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest req) {
                        //contains validation errors
        String message = ex.getBindingResult()
                .getFieldErrors()      //gets the list of errors
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));//join them in one long String

        ErrorResponse error = new ErrorResponse(
                message,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //for generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex , HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),req.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductImageNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleProductImageNotFoundException(Exception ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),req.getRequestURI());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse>handleFileStorageException(Exception ex,HttpServletRequest req){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),req.getRequestURI());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileInvalidException.class)
    public ResponseEntity<ErrorResponse>handleFileInvalidException(Exception ex,HttpServletRequest req) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(), req.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }



}
