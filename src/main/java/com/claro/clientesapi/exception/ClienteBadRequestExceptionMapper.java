package com.claro.clientesapi.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.springframework.web.server.ResponseStatusException;

@Provider
public class ClienteBadRequestExceptionMapper implements ExceptionMapper<ClienteBadRequestException> {

    @Override
    public Response toResponse(ClienteBadRequestException ex) {

        ClienteExceptionResponse response=new ClienteExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        response.setTimestamp(System.currentTimeMillis());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}
