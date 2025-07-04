package com.demo.poc.commons.injection;

import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.commons.tcp.ConnectionServer;
import com.demo.poc.entrypoint.DebtFinderRouterTCP;
import com.demo.poc.entrypoint.debts.repository.DebtCsvRepositoryImpl;
import com.demo.poc.entrypoint.debts.service.DebtFinderService;
import com.demo.poc.entrypoint.debts.service.DebtFinderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;

import static com.demo.poc.commons.constants.Constant.*;

@Slf4j
public class InjectorConfig extends AbstractModule {

    @Override
    protected void configure() {

        bind(DebtCsvRepositoryImpl.class);
        bind(DebtFinderService.class).to(DebtFinderServiceImpl.class);
        bind(ObjectMapper.class);
        bind(DebtFinderRouterTCP.class);
        bind(ServerSocket.class).toProvider(ServerSocketProvider.class);
        bind(ConnectionServer.class);

    }

    static class ServerSocketProvider implements Provider<ServerSocket> {

        @Override
        public ServerSocket get() {
            int port = Integer.parseInt(PropertiesReader.getProperty("application.port"));
            try {
                log.info(GREEN + BOLD + "Application started on port " + port + RESET);
                return new ServerSocket(port);
            } catch (IOException exception) {
                throw new RuntimeException("Error creating socket: " + exception.getMessage(), exception);
            }
        }
    }
}
