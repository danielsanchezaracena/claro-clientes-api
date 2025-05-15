package com.claro.clientesapi.utils;

import com.claro.clientesapi.dto.ClienteRequestDTO;
import com.claro.clientesapi.dto.DireccionResponseDTO;
import com.claro.clientesapi.entity.Cliente;

import java.util.List;

public class ClienteUtils {

    public static List<DireccionResponseDTO> getDireccionesParaResponse(Cliente cliente){
        return cliente.getDirecciones().stream().map(d->{
            DireccionResponseDTO dDTO=new DireccionResponseDTO();
            dDTO.setDireccion(d.getDireccion());
            dDTO.setActivo(d.isActivo());
            dDTO.setId(d.getId());
            return dDTO;
        }).toList();
    }

    public static boolean isInValidRequest(ClienteRequestDTO request){
      return request.getNombre()==null || request.getNombre().isBlank()
            || request.getApellido()==null || request.getApellido().isBlank()
              || request.getDirecciones()==null || request.getDirecciones().isEmpty();
    }

}
