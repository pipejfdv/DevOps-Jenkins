package com.uninpahu.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias - Calculadora (Laboratorio CI Semana 2)")
class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new Calculadora();
    }

    @Test
    @DisplayName("Suma de dos numeros positivos")
    void testSumar() {
        assertEquals(7, calculadora.sumar(3, 4));
    }

    @Test
    @DisplayName("Resta de dos numeros")
    void testRestar() {
        assertEquals(1, calculadora.restar(4, 3));
    }

    @Test
    @DisplayName("Multiplicacion de dos numeros")
    void testMultiplicar() {
        assertEquals(12, calculadora.multiplicar(3, 4));
    }

    @Test
    @DisplayName("Division exacta entre dos numeros")
    void testDividir() {
        assertEquals(2.0, calculadora.dividir(8, 4));
    }

    @Test
    @DisplayName("Division por cero debe lanzar excepcion")
    void testDividirPorCero() {
        assertThrows(ArithmeticException.class, () -> calculadora.dividir(5, 0));
    }

    @Test
    @DisplayName("Detecta correctamente numeros primos")
    void testEsPrimo() {
        assertTrue(calculadora.esPrimo(7));
        assertFalse(calculadora.esPrimo(9));
        assertFalse(calculadora.esPrimo(1));
    }
}
