package com.claro.clientesapi.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ClienteNoEncontradoExceptionMapper implements ExceptionMapper<ClienteNoEncontradoException> {

    @Override
    public Response toResponse(ClienteNoEncontradoException ex) {
        ClienteExceptionResponse response=new ClienteExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        response.setTimestamp(System.currentTimeMillis());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}
