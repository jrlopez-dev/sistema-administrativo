package com.sistemaadministrativo.service;

import com.sistemaadministrativo.model.entity.Venta;
import com.sistemaadministrativo.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    public Venta getVentaById(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    public Venta saveVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    // Consultas personalizadas
    public List<Object[]> getTop3ProductosMasVendidos() {
        return ventaRepository.findTop3ProductosMasVendidos();
    }

    public BigDecimal getIngresoTotalUltimoMes() {
        LocalDate inicioMes = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
        return ventaRepository.findIngresoTotalEntreFechas(inicioMes, finMes);
    }
}
