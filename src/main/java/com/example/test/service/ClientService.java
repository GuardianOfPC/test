package com.example.test.service;

import com.example.test.model.ClientDTO;
import com.example.test.model.ClientMapper;
import com.example.test.model.ClientResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientService {
    private final OkHttpClient httpClient = new OkHttpClient();

    private final ClientMapper clientMapper;

    public ClientService(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    private static final String WIREMOCK_URL = "http://localhost:8089";

    public ClientDTO getClientById(Long id) {
        Request request = new Request.Builder()
                .url(WIREMOCK_URL + "/client-service?id=" + id)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();

            ObjectMapper objectMapper = new ObjectMapper();
            ClientResponse clientResponse = objectMapper.readValue(responseBody, ClientResponse.class);

            return clientMapper.mapToClientDTO(clientResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
