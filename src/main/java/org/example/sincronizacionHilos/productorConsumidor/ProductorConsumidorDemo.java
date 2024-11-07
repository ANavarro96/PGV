package org.example.sincronizacionHilos.productorConsumidor;

public class ProductorConsumidorDemo {
    public static void main(String[] args) {
        ColaCompartida<Integer> cola = new ColaCompartida<>(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new Productor(cola)).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Consumidor(cola)).start();
        }

        // no pongo joins porque esto es infinito, pero lo paráis si fuese necesario.
    }
}

