package com.claro.clientesapi.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ClienteGlobalExceptionMapper implements ExceptionMapper<Exception> {


    @Override
    public Response toResponse(Exception ex) {
        ClienteExceptionResponse response=new ClienteExceptionResponse();
        response.setMessage("ERROR. Contacta el administrador");
        response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
        response.setTimestamp(System.currentTimeMillis());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}
