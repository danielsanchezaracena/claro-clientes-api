package com.claro.clientesapi.service;

import com.claro.clientesapi.dto.ClienteRequestDTO;
import com.claro.clientesapi.entity.Cliente;
import com.claro.clientesapi.entity.Direccion;
import com.claro.clientesapi.exception.ClienteNoEncontradoException;
import com.claro.clientesapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    ClienteServiceImpl(ClienteRepository clienteRepository){
        this.clienteRepository=clienteRepository;
    }

    @Override
    public Cliente crearCliente(ClienteRequestDTO request) {

       Cliente cliente=new Cliente();
       cliente.setNombre(request.getNombre());
       cliente.setApellido(request.getApellido());
       cliente.setActivo(true);
       cliente.setDirecciones(request.getDirecciones().stream().map(d->{
           Direccion dir=new Direccion();
           dir.setDireccion(d.getDireccion());
           dir.setCliente(cliente);
           dir.setActivo(true);
           return dir;
       }).toList());

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getCliente(Long id) {

        Optional<Cliente> optCliente=clienteRepository.findById(id);

        if(optCliente.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente no encontrado con el id:"+id);
        }

        return optCliente.get();
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public void eliminarCliente(Long id) {
        Optional<Cliente> optCliente = clienteRepository.findById(id);

        if(optCliente.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente no encontrado con el id:"+id);
        }

        clienteRepository.delete(optCliente.get());
    }

    @Override
    public Cliente modificarCliente(Long id, ClienteRequestDTO request) {

        Optional<Cliente> optCliente= clienteRepository.findById(id);

        if(optCliente.isEmpty()){
            throw new ClienteNoEncontradoException("Cliente no encontrado con el id:"+id);
        }

        Cliente cliente=optCliente.get();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());

        return clienteRepository.save(cliente);


    }
}
