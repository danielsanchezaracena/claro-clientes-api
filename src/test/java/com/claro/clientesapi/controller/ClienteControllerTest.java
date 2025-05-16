package com.claro.clientesapi.controller;

import com.claro.clientesapi.dto.ClienteRequestDTO;
import com.claro.clientesapi.dto.ClienteResponseDTO;
import com.claro.clientesapi.service.ClienteService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {

    private ClienteService clienteService;
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        clienteService = mock(ClienteService.class);
        clienteController = new ClienteController(clienteService);
    }

    @Test
    public void testCrearCliente_ReturnsCreatedResponse() {

        ClienteRequestDTO requestDTO = new ClienteRequestDTO();
        requestDTO.setNombre("Juan");
        requestDTO.setApellido("Martinez");
        ClienteResponseDTO responseDTO = new ClienteResponseDTO();

        when(clienteService.crearCliente(requestDTO)).thenReturn(responseDTO);


        Response response = clienteController.crearCliente(requestDTO);


        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(responseDTO, response.getEntity());
        verify(clienteService, times(1)).crearCliente(requestDTO);
    }

    @Test
    public void testGetClientes_ReturnsOkResponse() {

        ClienteResponseDTO c1 = new ClienteResponseDTO();
        ClienteResponseDTO c2 = new ClienteResponseDTO();
        List<ClienteResponseDTO> clientes = Arrays.asList(c1, c2);

        when(clienteService.getAllClientes()).thenReturn(clientes);


        Response response = clienteController.getClientes();


        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(clientes, response.getEntity());
        verify(clienteService, times(1)).getAllClientes();
    }

    @Test
    public void testGetCliente_ReturnsOkResponse() {

        long id=1;

        ClienteResponseDTO cliente = new ClienteResponseDTO();

        when(clienteService.getCliente(anyLong())).thenReturn(cliente);

        Response response = clienteController.getCliente(id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(cliente, response.getEntity());
        verify(clienteService, times(1)).getCliente(anyLong());
    }

}
