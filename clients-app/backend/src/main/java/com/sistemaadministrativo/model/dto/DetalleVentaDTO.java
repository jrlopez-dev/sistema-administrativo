package com.sistemaadministrativo.model.dto;

import com.sistemaadministrativo.model.entity.Producto;
import com.sistemaadministrativo.model.entity.Venta;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleVentaDTO {
    private Long id;
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private BigDecimal subtotal;
}
