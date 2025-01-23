package com.sistemaadministrativo.service;

import com.sistemaadministrativo.model.entity.DetalleVenta;
import com.sistemaadministrativo.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> getAllDetalles() {
        return detalleVentaRepository.findAll();
    }

    public DetalleVenta getDetalleById(Long id) {
        return detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado con ID: " + id));
    }

    public DetalleVenta saveDetalle(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public void deleteDetalle(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}
