package org.example.sincronizacionHilos.semaforo;

import java.util.concurrent.Semaphore;

public class Cancha {
    // Recursos compartidos
    Semaphore balonesS = new Semaphore(10);
    Semaphore canastasS = new Semaphore(4);

    // coger(8,1)
    // libres 7 balones
    public void coger(int balones, int canastas) throws InterruptedException {
        balonesS.acquire(balones); // BLOQUEADO
        canastasS.acquire(canastas);
    }


    public void dejar(int balones, int canastas){
        balonesS.release(balones);
        canastasS.release(canastas);
    }

}
