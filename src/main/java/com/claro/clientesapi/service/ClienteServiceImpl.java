package com.claro.clientesapi.service;

import com.claro.clientesapi.dto.ClienteRequestDTO;
import com.claro.clientesapi.dto.ClienteResponseDTO;
import com.claro.clientesapi.entity.Cliente;
import com.claro.clientesapi.entity.Direccion;
import com.claro.clientesapi.exception.ClienteBadRequestException;
import com.claro.clientesapi.exception.ClienteNoEncontradoException;
import com.claro.clientesapi.repository.ClienteRepository;
import com.claro.clientesapi.utils.ClienteUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    ClienteServiceImpl(ClienteRepository repository){
        this.repository = repository;
    }

    @Override
    public ClienteResponseDTO crearCliente(ClienteRequestDTO request) {

        if(ClienteUtils.isInValidRequest(request))
            throw new ClienteBadRequestException("Request invalido. Ingresa todos los campos requeridos.");

       Cliente cliente=new Cliente();
       cliente.setNombre(request.getNombre());
       cliente.setApellido(request.getApellido());
       cliente.setActivo(true);
       cliente.setDirecciones(request.getDirecciones().stream().map(d->{
           Direccion dir=new Direccion();
           dir.setDireccion(d);
           dir.setCliente(cliente);
           dir.setActivo(true);
           return dir;
       }).toList());

       Cliente c= repository.save(cliente);

       ClienteResponseDTO response=new ClienteResponseDTO();
       response.setId(c.getId());
       response.setNombre(c.getNombre());
       response.setApellido(c.getApellido());
       response.setActivo(c.isActivo());
       response.setDirecciones(ClienteUtils.getDireccionesParaResponse(c));

        return response;
    }

    @Override
    public ClienteResponseDTO getCliente(Long id) {

        Optional<Cliente> optCliente= repository.findById(id);

        if(optCliente.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente no encontrado con el id "+id);
        }
            Cliente c=optCliente.get();

            ClienteResponseDTO response=new ClienteResponseDTO();
            response.setNombre(c.getNombre());
            response.setApellido(c.getApellido());
            response.setId(c.getId());
            response.setDirecciones(ClienteUtils.getDireccionesParaResponse(c));
            response.setActivo(c.isActivo());
        return response;
    }

    @Override
    public List<ClienteResponseDTO> getAllClientes() {
        List<ClienteResponseDTO> clientes=new ArrayList<>();

        repository.findAll().forEach(c->{
            ClienteResponseDTO response=new ClienteResponseDTO();
            response.setId(c.getId());
            response.setNombre(c.getNombre());
            response.setApellido(c.getApellido());
            response.setActivo(c.isActivo());
            response.setDirecciones(ClienteUtils.getDireccionesParaResponse(c));
            clientes.add(response);
        });

        return clientes;
    }

    @Override
    public void eliminarCliente(Long id) {
        Optional<Cliente> optCliente = repository.findById(id);

        if(optCliente.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente no encontrado con el id "+id);
        }

        repository.delete(optCliente.get());
    }

    @Override
    public ClienteResponseDTO modificarCliente(Long id, ClienteRequestDTO request) {

        Optional<Cliente> optCliente= repository.findById(id);

        if(optCliente.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente no encontrado con el id "+id);
        }

        if(ClienteUtils.isInValidRequest(request))
            throw new ClienteBadRequestException("Request invalido. Ingresa todos los campos requeridos.");

        Cliente cliente=optCliente.get();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setDirecciones(request.getDirecciones().stream().map(d->{
            Direccion dir=new Direccion();
            dir.setDireccion(d);
            dir.setCliente(cliente);
            dir.setActivo(true);
            return dir;
        }).toList());

        Cliente clienteModificado=repository.save(cliente);

        ClienteResponseDTO response=new ClienteResponseDTO();
            response.setNombre(clienteModificado.getNombre());
            response.setApellido(clienteModificado.getApellido());
            response.setId(clienteModificado.getId());
            response.setDirecciones(ClienteUtils.getDireccionesParaResponse(clienteModificado));
            response.setActivo(clienteModificado.isActivo());

        return response;
    }
}
