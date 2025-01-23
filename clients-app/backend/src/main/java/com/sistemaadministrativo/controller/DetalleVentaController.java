package com.sistemaadministrativo.controller;

import com.sistemaadministrativo.model.entity.DetalleVenta;
import com.sistemaadministrativo.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta")
@CrossOrigin(origins = "http://localhost:3000")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVenta> getAllDetalles() {
        return detalleVentaService.getAllDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> getDetalleById(@PathVariable Long id) {
        DetalleVenta detalle = detalleVentaService.getDetalleById(id);
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> saveDetalle(@RequestBody DetalleVenta detalleVenta) {
        DetalleVenta nuevoDetalle = detalleVentaService.saveDetalle(detalleVenta);
        return ResponseEntity.ok(nuevoDetalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id) {
        detalleVentaService.deleteDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
