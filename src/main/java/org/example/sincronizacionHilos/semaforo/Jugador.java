package org.example.sincronizacionHilos.semaforo;

import java.util.concurrent.Semaphore;

public class Jugador implements Runnable{
    public static final Semaphore semaforo = new Semaphore(3);


    @Override
    public void run() {
        while(true){
            System.out.println("Hola, soy " + Thread.currentThread().getName() + " y me voy a echar una partida ");
            System.out.println("Hay un total de " + semaforo.getQueueLength() + " aqui esperando");
            try {
                semaforo.acquire();
                System.out.println("Vaya partidica aqui que bien " + Thread.currentThread().getName() + " ya he entrado");

                Thread.sleep(3000);
                semaforo.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
