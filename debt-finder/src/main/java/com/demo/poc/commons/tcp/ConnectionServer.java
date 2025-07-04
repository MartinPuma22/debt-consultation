package com.demo.poc.commons.tcp;

import com.demo.poc.entrypoint.DebtFinderRouterTCP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class ConnectionServer {

  private final Provider<DebtFinderRouterTCP> tcpRouterProvider;
  private final ServerSocket serverSocket;

  @Inject
  public ConnectionServer(Provider<DebtFinderRouterTCP> tcpRouterProvider,
                          ServerSocket serverSocket) {
    this.tcpRouterProvider = tcpRouterProvider;
    this.serverSocket = serverSocket;
  }

  public void start() throws IOException {
    Socket socket;
    while (true) {
      socket = serverSocket.accept();
      DebtFinderRouterTCP router = tcpRouterProvider.get();
      router.setSocket(socket);
      router.start();
      log.info("A new connection was detected");
    }
  }

}
