package com.sistemaadministrativo.repository;

import com.sistemaadministrativo.model.entity.Cliente;
import com.sistemaadministrativo.model.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT p.nombre, SUM(d.cantidad) AS totalVendidos " +
            "FROM DetalleVenta d JOIN d.producto p " +
            "GROUP BY p.id ORDER BY totalVendidos DESC")
    List<Object[]> findTop3ProductosMasVendidos();

    @Query("SELECT v.cliente " +
            "FROM Venta v " +
            "GROUP BY v.cliente.id ORDER BY SUM(v.total) DESC")
    Cliente findClienteConMayorIngreso();

    @Query("SELECT SUM(v.total) " +
            "FROM Venta v " +
            "WHERE v.fecha BETWEEN :inicio AND :fin")
    BigDecimal findIngresoTotalEntreFechas(LocalDate inicio, LocalDate fin);
}
