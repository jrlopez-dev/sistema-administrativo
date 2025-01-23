package com.sistemaadministrativo.common.exception;


import com.sistemaadministrativo.model.dto.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionDTO> handleMarvelApiClientException(ServiceException ex) {
        ExceptionDTO response = ExceptionDTO.builder()
                .title(ex.getTitle())
                .type(ex.getType())
                .detail(ex.getDetail())
                .build();

        return new ResponseEntity<ExceptionDTO>(response, ex.getHttpStatus());
    }
}
