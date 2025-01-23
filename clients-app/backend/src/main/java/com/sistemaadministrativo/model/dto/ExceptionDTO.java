package com.sistemaadministrativo.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class ExceptionDTO {
    private String type;
    private String title;
    private String detail;
}
