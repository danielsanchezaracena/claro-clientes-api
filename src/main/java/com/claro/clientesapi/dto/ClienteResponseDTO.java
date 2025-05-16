package com.claro.clientesapi.dto;

import java.util.List;

public class ClienteResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private boolean activo;
    private List<DireccionResponseDTO> direcciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<DireccionResponseDTO> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionResponseDTO> direcciones) {
        this.direcciones = direcciones;
    }
}
