package org.example.sincronizacionHilos.ejercicioSenyoras;

import java.util.concurrent.Semaphore;

public class PasoCebraSemaforos {
    static Semaphore semaphore = new Semaphore(5);
    int senyorasEsperando = 0;

    public void entrarCiclista(String nombre) throws InterruptedException {
        semaphore.acquire();
    }

    public void salirCiclista(String nombre) throws InterruptedException {
        semaphore.release();
    }

    public void entrarSenyora(String nombre) throws InterruptedException {
        semaphore.acquire(5);
    }

    public void salirSenyora(String nombre) throws InterruptedException {
        semaphore.release(5);
    }
}
