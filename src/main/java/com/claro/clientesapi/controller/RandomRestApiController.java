package com.claro.clientesapi.controller;

import com.claro.clientesapi.external.ExternalApiService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/randomrest")
@Produces(MediaType.APPLICATION_JSON)
public class RandomRestApiController {

    private final ExternalApiService apiService;

    @Inject
    public RandomRestApiController(ExternalApiService apiService){
        this.apiService=apiService;
    }

    @GET
    public Response getRandomREST(){
        String result=apiService.callExternalApi();
        return Response.ok(result).build();
    }
}
