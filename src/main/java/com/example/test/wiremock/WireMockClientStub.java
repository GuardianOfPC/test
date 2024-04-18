package com.example.test.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

@Component
public class WireMockClientStub {
    private static final int PORT = 8089;

    static {
        WireMockServer wireMockServer = new WireMockServer(PORT);
        wireMockServer.start();
        configureFor("localhost", PORT);
        clientStub(1L);
    }

    public static void clientStub(Long id) {
        stubFor(get(urlPathEqualTo("/client-service"))
                .withQueryParam("id", equalTo(id.toString()))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\": " + id + ", \"clientFirstName\": \"Иван\", \"clientLastName\": \"Иванов\" }")));
    }
}
