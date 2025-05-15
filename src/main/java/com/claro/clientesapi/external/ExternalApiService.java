package com.claro.clientesapi.external;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    private final Client client;
    private final String url="https://api.chucknorris.io/jokes/random";

    public ExternalApiService(Client client){
        this.client=client;
    }

    public String callExternalApi(){
        WebTarget target =client.target(url);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if(response.getStatus()==200){
            return response.readEntity(String.class);
        }else{
            throw new RuntimeException("Error. Code:"+response.getStatus());
        }
    }
}
