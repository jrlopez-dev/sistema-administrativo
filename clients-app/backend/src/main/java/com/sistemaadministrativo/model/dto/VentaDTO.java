package com.sistemaadministrativo.model.dto;

import com.sistemaadministrativo.model.entity.Cliente;
import com.sistemaadministrativo.model.entity.DetalleVenta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private Cliente cliente;
    private BigDecimal total;
    private List<DetalleVenta> detalles;
}
