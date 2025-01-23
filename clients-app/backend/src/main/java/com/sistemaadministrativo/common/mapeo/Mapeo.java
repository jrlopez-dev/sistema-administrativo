package com.sistemaadministrativo.common.mapeo;

import com.sistemaadministrativo.model.dto.ClienteDTO;
import com.sistemaadministrativo.model.dto.DetalleVentaDTO;
import com.sistemaadministrativo.model.dto.ProductoDTO;
import com.sistemaadministrativo.model.dto.VentaDTO;
import com.sistemaadministrativo.model.entity.Cliente;
import com.sistemaadministrativo.model.entity.DetalleVenta;
import com.sistemaadministrativo.model.entity.Producto;
import com.sistemaadministrativo.model.entity.Venta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Mapeo {

    ClienteDTO converterClienteDTO(Cliente cliente);
    Cliente converterCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> converterClienteDTO(List<Cliente> clienteList);
    List<Cliente> converterCliente(List<ClienteDTO> clienteDTOList);

    ProductoDTO converterProductoDTO(Producto producto);
    Producto converterProducto(ProductoDTO productoDTO);
    List<Producto> converterProducto(List<ProductoDTO> productoDTOList);
    List<ProductoDTO> converterProductoDTO(List<Producto> productoList);

    VentaDTO converterVentaDTO(Venta venta);
    Venta converterVenta(VentaDTO ventaDTO);
    List<VentaDTO> converterVentaDTO(List<Venta> ventaList);
    List<Venta> converterVenta(List<VentaDTO> ventaDTOList);

    DetalleVentaDTO converterDetalleVentaDTO(DetalleVenta detalleVenta);
    DetalleVenta converterDetalleVenta(DetalleVentaDTO detalleVentaDTO);
    List<DetalleVentaDTO> converterDetalleVentaDTO(List<DetalleVenta> detalleVentaList);
    List<DetalleVenta> converterDetalleVenta(List<DetalleVentaDTO> detalleVentaDTOList);

}
