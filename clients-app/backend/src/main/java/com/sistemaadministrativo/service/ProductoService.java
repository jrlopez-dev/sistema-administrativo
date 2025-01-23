package com.sistemaadministrativo.service;

import com.sistemaadministrativo.common.mapeo.Mapeo;
import com.sistemaadministrativo.model.dto.ProductoDTO;
import com.sistemaadministrativo.model.entity.Producto;
import com.sistemaadministrativo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private Mapeo mapeo;

    public List<ProductoDTO> getAllProductos() {
        return mapeo.converterProductoDTO(productoRepository.findAll());
    }

    public ProductoDTO getProductoById(Long id) {
        return mapeo.converterProductoDTO(productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id))) ;
    }

    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        productoRepository.save(producto);
        return productoDTO;
    }

    public ProductoDTO updateProducto(Long id, ProductoDTO producto) {
        ProductoDTO existingProducto = getProductoById(id);
        Producto updatedProducto = new Producto();
        updatedProducto.setNombre(producto.getNombre());
        updatedProducto.setPrecio(producto.getPrecio());
        updatedProducto.setStock(producto.getStock());
        productoRepository.save(updatedProducto);
        return mapeo.converterProductoDTO(updatedProducto);
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

}
