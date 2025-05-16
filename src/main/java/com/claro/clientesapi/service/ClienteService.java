package com.claro.clientesapi.service;

import com.claro.clientesapi.dto.ClienteRequestDTO;
import com.claro.clientesapi.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    ClienteResponseDTO crearCliente(ClienteRequestDTO request);

    ClienteResponseDTO getCliente(Long id);

    List<ClienteResponseDTO> getAllClientes();

    void eliminarCliente(Long id);

    ClienteResponseDTO modificarCliente(Long id, ClienteRequestDTO request);

}
