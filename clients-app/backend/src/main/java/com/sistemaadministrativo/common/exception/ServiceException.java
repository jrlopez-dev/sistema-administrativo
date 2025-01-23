package com.sistemaadministrativo.common.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String type;
    private String title;
    private String detail;
    private HttpStatus httpStatus;

    public ServiceException(String type, String title, String detail, HttpStatus httpStatus) {
        super();
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    public ServiceException(String detail) {
        super();
        this.type = "Bad Request";
        this.title = "Validacion";
        this.detail = detail;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
