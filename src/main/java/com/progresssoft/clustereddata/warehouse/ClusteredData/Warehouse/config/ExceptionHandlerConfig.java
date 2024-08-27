package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.config;

import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.exception.BusinessException;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.exception.TechnicalException;
import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.dto.ErrorResponseDTO;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException ex) {

        ErrorResponseDTO errorDTO = ErrorResponseDTO.builder()
                .errorCode(messageSource.getMessage(ex.getClass().getSimpleName() + "-CODE", null, LocaleContextHolder.getLocale()))
                .errorMessage(messageSource.getMessage(ex.getClass().getSimpleName() + "-MESSAGE", null, LocaleContextHolder.getLocale()))
                .build();

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ErrorResponseDTO> handleTechnicalException(TechnicalException ex) {

        ErrorResponseDTO errorDTO = ErrorResponseDTO.builder()
                .errorCode(messageSource.getMessage(ex.getClass().getSimpleName() + "-CODE", null, LocaleContextHolder.getLocale()))
                .errorMessage(messageSource.getMessage(ex.getClass().getSimpleName() + "-MESSAGE", null, LocaleContextHolder.getLocale()))
                .build();

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleViolationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(field, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request please send all the required fields");
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleException(RuntimeException ex) {

        return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
    }
}
