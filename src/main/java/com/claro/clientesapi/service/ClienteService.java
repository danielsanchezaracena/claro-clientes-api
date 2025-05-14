package com.claro.clientesapi.service;

import com.claro.clientesapi.dto.ClienteRequestDTO;
import com.claro.clientesapi.entity.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente crearCliente(ClienteRequestDTO request);

    Cliente getCliente(Long id);

    List<Cliente> getAllClientes();

    void eliminarCliente(Long id);

    Cliente modificarCliente(Long id, ClienteRequestDTO request);

}
