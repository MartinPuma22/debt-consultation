package com.demo.poc.entrypoint.exchange.router;

import com.demo.poc.entrypoint.exchange.dto.ExchangeRateResponseDto;
import com.demo.poc.entrypoint.exchange.service.ExchangeRateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.demo.poc.commons.tcp.TCPResourceHelper.closeResource;

public class ExchangeRateRouterTCP extends Thread {

    private final ExchangeRateService exchangeRateService;
    private final ObjectMapper objectMapper;

    private Socket socket;

    @Inject
    public ExchangeRateRouterTCP(ExchangeRateService exchangeRateService, ObjectMapper objectMapper) {

        this.exchangeRateService = exchangeRateService;
        this.objectMapper = objectMapper;

    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true)
        ) {

            String endpoint = inputReader.readLine();
            boolean success = false;

            if (endpoint.matches("^api/v1/exchange-rate/[A-Z]{3}/[A-Z]{3}$")) {

                String origin = endpoint.split("/")[3].trim();
                String target = endpoint.split("/")[4].trim();
                ExchangeRateResponseDto response = exchangeRateService.generateExchangeRate(origin, target);
                String jsonResponse = objectMapper.writeValueAsString(response);
                outputWriter.println(jsonResponse);
                success = true;
            }

            if (!success)
                throw new IllegalArgumentException("The request '" + endpoint + "' was not processed successfully");

        } catch (IOException exception) {
            throw new RuntimeException("TCP error: " + exception.getMessage(), exception);

        } finally {
            closeResource(socket);
        }
    }
}
