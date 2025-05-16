package com.claro.clientesapi.controller;

import com.claro.clientesapi.dto.CalculatorAddRequest;
import com.claro.clientesapi.utils.XmlUtil;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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

    private final Client client;

    @Inject
    public RandomSoapApiController(Client client) {
        this.client = client;
    }

    @POST
    public Response randomSOAPEndpoint() throws Exception {
        String uri = "http://www.dneonline.com/calculator.asmx";

        CalculatorAddRequest dto = new CalculatorAddRequest(10, 20);

        String bodyXml = XmlUtil.toXml(dto);

        StringBuilder sbRequest=new StringBuilder();

        sbRequest.append("""
               <?xml version="1.0" encoding="utf-8"?>
                <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
                  <soap12:Body>
               """);
        sbRequest.append(bodyXml);
        sbRequest.append("""
                  </soap12:Body>
                </soap12:Envelope>
               """);


        Response response = client
                .target(uri)
                .request()
                .header("Content-Type", "application/soap+xml; charset=utf-8")
                .post(Entity.entity(sbRequest.toString(), MediaType.TEXT_XML));

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
