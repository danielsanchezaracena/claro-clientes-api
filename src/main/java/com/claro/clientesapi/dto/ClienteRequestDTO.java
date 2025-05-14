package com.claro.clientesapi.dto;

import java.util.List;

public class ClienteRequestDTO {

    private String nombre;

    private String apellido;

    private List<DireccionRequestDTO> direcciones;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<DireccionRequestDTO> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionRequestDTO> direcciones) {
        this.direcciones = direcciones;
    }
}
