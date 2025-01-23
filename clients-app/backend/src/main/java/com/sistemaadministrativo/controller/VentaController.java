package com.sistemaadministrativo.controller;

import com.sistemaadministrativo.model.entity.Venta;
import com.sistemaadministrativo.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:3000")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Venta venta = ventaService.getVentaById(id);
        return ResponseEntity.ok(venta);
    }

    @PostMapping
    public ResponseEntity<Venta> saveVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaService.saveVenta(venta);
        return ResponseEntity.ok(nuevaVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints adicionales
    @GetMapping("/top-productos")
    public List<Object[]> getTop3ProductosMasVendidos() {
        return ventaService.getTop3ProductosMasVendidos();
    }

    @GetMapping("/ingreso-mensual")
    public BigDecimal getIngresoTotalUltimoMes() {
        return ventaService.getIngresoTotalUltimoMes();
    }
}
