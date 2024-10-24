package org.example.sincronizacionHilos.productorConsumidor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProductorConsumidor {

        private final int LIMITE = 10;
        private final Queue<Integer> cola = new LinkedList<>();
        // Esta interfaz representa una cola FIFO (FIrst In First Out).
        // En una cola solo podemos añadir por el final de la misma y eliminar el primero. Como en el super.

        class Productor implements Runnable {
            private int id;

            public Productor(int id) {
                this.id = id;
            }

            @Override
            public void run() {
                int valor = 0;
                try {
                    while (true) {
                        synchronized (ProductorConsumidor.this) {
                            // Mientras la cola esté llena....
                            while (cola.size() == LIMITE) {
                                ProductorConsumidor.this.wait();  // Espero a que me avisen, no voy a producir nada
                                // si la cola ya está llena
                            }

                            System.out.println("Productor " + id + " produce: " + valor);
                            cola.add(valor++);
                            Thread.sleep(1000);  // Simulamos tiempo

                            ProductorConsumidor.this.notifyAll();  // Notifica a todo el mundo

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        class Consumidor implements Runnable {
            private int id;

            public Consumidor(int id) {
                this.id = id;
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (ProductorConsumidor.this) {
                            // Mientras la cola esté vacía...
                            while (cola.isEmpty()) {
                                ProductorConsumidor.this.wait();  // Esperamos a que alguien me avise,
                                // no voy a consumir estando la cola vacía...
                            }

                            // Esto hace POP!
                            // Es decir, obtenemos el primer elemento de la cola, y lo eliminamos.
                            // El resto se mueven una posición hacia adelante.
                            int valor = cola.poll();
                            System.out.println("Consumidor " + id + " consume: " + valor);
                            Thread.sleep(1000);  // Simula tiempo de consumo
                            ProductorConsumidor.this.notifyAll();  // Notificamos a todo el mundo

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            ProductorConsumidor pc = new ProductorConsumidor();

            // Creando y lanzando 3 productores
            for (int i = 1; i <= 3; i++) {
                Thread productor = new Thread(pc.new Productor(i));
                productor.start();
            }

            // Creando y lanzando 3 consumidores
            for (int i = 1; i <= 3; i++) {
                Thread consumidor = new Thread(pc.new Consumidor(i));
                consumidor.start();
            }
        }
    }

