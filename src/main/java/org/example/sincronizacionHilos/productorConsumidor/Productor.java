package org.example.sincronizacionHilos.productorConsumidor;

class Productor implements Runnable {
    private final ColaCompartida<Integer> cola;

    public Productor(ColaCompartida<Integer> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                cola.put(i);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
