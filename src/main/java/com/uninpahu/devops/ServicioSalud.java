package com.uninpahu.devops;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class ServicioSalud {
    public static void iniciar() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0);
        server.createContext("/salud", ex -> {
            byte[] r = "OK".getBytes();
            ex.sendResponseHeaders(200, r.length);
            ex.getResponseBody().write(r);
            ex.close();
        });
        server.setExecutor(null);
        server.start();
        System.out.println("Servicio de salud iniciado en :8080");
    }
}
