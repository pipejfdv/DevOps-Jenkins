package com.uninpahu.devops;

public class App {
    public static void main(String[] args) throws Exception {
        ServicioSalud.iniciar();
        System.out.println("Calculadora CI iniciada. Endpoint salud en http://localhost:8080/salud");
        Thread.currentThread().join();
    }
}
