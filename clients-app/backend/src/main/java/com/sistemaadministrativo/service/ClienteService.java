package com.sistemaadministrativo.service;

import com.sistemaadministrativo.common.exception.ServiceException;
import com.sistemaadministrativo.common.mapeo.Mapeo;
import com.sistemaadministrativo.model.dto.ClienteDTO;
import com.sistemaadministrativo.model.entity.Cliente;
import com.sistemaadministrativo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private Mapeo mapeo;

    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clienteDTOs = new ArrayList<>();
        if (!clientes.isEmpty()) {
            clienteDTOs = mapeo.converterClienteDTO(clientes);
        }else{
            throw new ServiceException("No se encontraron clientes");
        }
        return clienteDTOs;
    }

    public ClienteDTO getClienteById(Long id) {
        return mapeo.converterClienteDTO(clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id))) ;
    }

    public ClienteDTO saveCliente(ClienteDTO clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCorreo(clienteDto.getCorreo());
        clienteRepository.save(cliente);
        return clienteDto;
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO cliente) {
        ClienteDTO existingCliente = getClienteById(id);
        Cliente clienteUpdate = new Cliente();
        clienteUpdate.setNombre(cliente.getNombre());
        clienteUpdate.setCorreo(cliente.getCorreo());
        clienteRepository.save(clienteUpdate);
        return cliente;
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
