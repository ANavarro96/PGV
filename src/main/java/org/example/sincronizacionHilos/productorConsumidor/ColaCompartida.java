package org.example.sincronizacionHilos.productorConsumidor;

import java.util.LinkedList;

class ColaCompartida<T> {
    private final LinkedList<T> cola = new LinkedList<>();
    private final int capacidad;

    public ColaCompartida(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void put(T item) throws InterruptedException {
        while (cola.size() == capacidad) {
            wait(); // espera si la cola está llena
        }
        cola.add(item);
        System.out.println("Producido: " + item);
        Thread.sleep(3000);
        notifyAll(); // notifica a los consumidores
    }

    public synchronized T take() throws InterruptedException {
        while (cola.isEmpty()) {
            wait(); // espera si la cola está vacía
        }
        T item = cola.removeFirst();
        System.out.println("Consumido: " + item);
        Thread.sleep(3000);
        notifyAll(); // notifica a los productores
        return item;
    }
}
