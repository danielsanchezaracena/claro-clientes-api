package com.claro.clientesapi.controller;

import com.claro.clientesapi.dto.CalculatorAddRestRequest;
import com.claro.clientesapi.dto.CalculatorAddSoapRequest;
import com.claro.clientesapi.external.ExternalSoapApiservice;
import com.claro.clientesapi.utils.XmlUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Path("/randomsoap")
@Produces(MediaType.APPLICATION_JSON)
public class RandomSoapApiController {

    private final ExternalSoapApiservice service;

    @Inject
    public RandomSoapApiController(ExternalSoapApiservice service) {
        this.service=service;
    }

    @POST
    public Response randomSOAPEndpoint(CalculatorAddRestRequest restRequest) throws Exception {

        Response response=service.callSOAPApi(restRequest);
        return response;
    }

}
