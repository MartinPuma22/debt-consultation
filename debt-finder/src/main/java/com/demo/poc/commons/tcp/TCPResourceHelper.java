package com.demo.poc.commons.tcp;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.Socket;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TCPResourceHelper {

  public static void closeResource(Socket socket) {
    try {
      if (socket != null) socket.close();
    } catch (IOException exception) {
      throw new RuntimeException("Error closing TCP connection: " + exception.getMessage(), exception);
    }
  }
}
