package com.claro.clientesapi.controller;

import com.claro.clientesapi.dto.ClienteRequestDTO;
import com.claro.clientesapi.dto.ClienteResponseDTO;
import com.claro.clientesapi.entity.Cliente;
import com.claro.clientesapi.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    private ClienteService clienteService;

    @POST
    public Response crearCliente(ClienteRequestDTO cliente){
        ClienteResponseDTO c=clienteService.crearCliente(cliente);
        return  Response.ok(c).status(Response.Status.CREATED).build();
    }

    @GET
    public Response getClientes(){
        List<ClienteResponseDTO> clientes=clienteService.getAllClientes();
      return Response.ok(clientes).status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    public Response getCliente(@PathParam("id") Long id){
        ClienteResponseDTO c=clienteService.getCliente(id);
        return Response.ok(c).status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarCliente(@PathParam("id") Long id){
        clienteService.eliminarCliente(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ClienteRequestDTO cliente) {
        ClienteResponseDTO c=clienteService.modificarCliente(id,cliente);
        return Response.ok(c).status(Response.Status.OK).build();
    }
}
