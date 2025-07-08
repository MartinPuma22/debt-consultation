package com.demo.poc.entrypoint;


import com.demo.poc.entrypoint.debts.dto.*;
import com.demo.poc.entrypoint.debts.service.DebtFinderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import static com.demo.poc.commons.tcp.TCPResourceHelper.closeResource;

public class DebtFinderRouterTCP extends Thread {

    private final DebtFinderService debtFinderService;
    private final ObjectMapper objectMapper;

    private Socket socket;

    @Inject
    public DebtFinderRouterTCP(DebtFinderService debtFinderService, ObjectMapper objectMapper) {

        this.debtFinderService = debtFinderService;
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

            if (endpoint.matches("^api/v1/debt-finder/debts/[0-9]+$")) {
                String id = endpoint.split("/")[4].trim();
                DebtDto response = debtFinderService.findById(Long.parseLong(id));
                String jsonResponse = objectMapper.writeValueAsString(response);
                outputWriter.println(jsonResponse);
                success = true;
            }
            if (endpoint.matches("^api/v1/debt-finder/debts/customers/[0-9]{8}$")){
                String customerDni = endpoint.split("/")[5].trim();
                DebtByDocumentDto response = debtFinderService.findByDni(String.valueOf(Long.parseLong(customerDni)));
                String jsonResponse = objectMapper.writeValueAsString(response);
                outputWriter.println(jsonResponse);
                success = true;
            }
            if (endpoint.matches("^api/v1/debt-finder/debts/currency/[A-Z]{3}$")){
                String currency = endpoint.split("/")[5].trim();
                List<DebtByCurrency> response = debtFinderService.findByCurrency(currency);
                String jsonResponse = objectMapper.writeValueAsString(response);
                outputWriter.println(jsonResponse);
                success = true;
            }
            if (endpoint.matches("^api/v1/debt-finder/debts/status/(true|false)")) {
                String status = endpoint.split("/")[5].trim();
                List<DebtsByStatusDto> response = debtFinderService.findByPaymentStatus(Boolean.parseBoolean(status));
                String jsonResponse = objectMapper.writeValueAsString(response);
                outputWriter.println(jsonResponse);
                success = true;
            }
            if (endpoint.matches("^api/v1/debt-finder/debts/amount/greater-than/\\d+(\\.\\d+)?$")) {
                String amount = endpoint.split("/")[6].trim();
                List<DebtsLeakedDto> response = debtFinderService.findDebtsGreaterThan(Double.parseDouble(amount));
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
