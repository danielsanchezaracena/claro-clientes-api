package com.claro.clientesapi.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;
import org.json.XML;

@Path("/randomsoap")
@Produces(MediaType.APPLICATION_JSON)
public class RandomSoapApiController {

    @GET
    public Response randomSOAPEndpoint(){
       final String endpointUrl = "http://www.dneonline.com/calculator.asmx";

        final String soapRequestXml ="""
                <?xml version="1.0" encoding="utf-8"?>
                <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
                  <soap12:Body>
                    <Add xmlns="http://tempuri.org/">
                      <intA>5</intA>
                      <intB>2</intB>
                    </Add>
                  </soap12:Body>
                </soap12:Envelope>
                """;

        Client client = ClientBuilder.newClient();

        Response response = client
                .target(endpointUrl)
                .request()
                .header("Content-Type", "application/soap+xml; charset=utf-8")
                .post(Entity.entity(soapRequestXml, MediaType.TEXT_XML));

        if(response.getStatus()!=200)
            throw new RuntimeException("Error. Code:"+response.getStatus());

        String responseXml = response.readEntity(String.class);

        JSONObject jsonObject = XML.toJSONObject(responseXml);
        String jsonResponse = jsonObject.toString(4);

        response.close();
        client.close();

        return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
    }

}
