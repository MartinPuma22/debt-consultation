package com.demo.poc;

import com.demo.poc.commons.injection.InjectorConfig;
import com.demo.poc.commons.tcp.ConnectionServer;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        Injector injector = Guice.createInjector(new InjectorConfig());
        ConnectionServer server = injector.getInstance(ConnectionServer.class);
        server.start();


    }
}