package org.example.sincronizacionHilos.productorConsumidor;

class Consumidor implements Runnable {
    private final ColaCompartida<Integer> cola;

    public Consumidor(ColaCompartida<Integer> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                cola.take();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


