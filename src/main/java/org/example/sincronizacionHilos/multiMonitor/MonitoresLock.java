package org.example.sincronizacionHilos.multiMonitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MonitoresLock implements Monitor{

    private final ReentrantLock monitor2 = new ReentrantLock();
    private final Condition condicion2 = monitor2.newCondition();

    // TODO: Esto habría que ir modificando su valor. Pensad la condición de que la cola no esté llena, o vacía...
    // Siempre en exclusión mutua! Tanto su modificación como su leído..
    boolean disponible = false;


    private final ReentrantLock monitor1 = new ReentrantLock();
    private final Condition condicion1 = monitor1.newCondition();
    @Override
    public void pedirMonitorSuma() throws InterruptedException {
        monitor1.lock();
        try {
            while (!disponible) {
                condicion1.await();  // Lo mismo que wait(). Suelto el cerrojo.
            }
        }
        finally {
            monitor1.unlock();
        }

    }

    @Override
    public void soltarMonitorSuma() throws InterruptedException {
        monitor1.lock();
        try {
            disponible = true;
           condicion1.signalAll();
        }
        finally {
            monitor1.unlock();
        }

    }

    @Override
    public void pedirMonitorResta() throws InterruptedException {
        monitor2.lock();
        try {
            while (!disponible) {
                condicion2.await();  // Lo mismo que wait(). Suelto el cerrojo.
            }
        }
        finally {
            monitor2.unlock();
        }

    }

    @Override
    public void soltarMonitorResta() throws InterruptedException {
        monitor2.lock();
        try {
            condicion2.signalAll();
        }
        finally {
            monitor2.unlock();
        }

    }
}
