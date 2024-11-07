package org.example.sincronizacionHilos.multiMonitor;

public class MonitoresObjetos implements Monitor{

        private final Object monitor1 = new Object();

        public void pedirMonitorSuma() throws InterruptedException {
            synchronized (monitor1) {
                System.out.println("Esperando en monitor1...");
                monitor1.wait();
                System.out.println("Despertado en monitor1!");
            }
        }

        public void soltarMonitorSuma() throws InterruptedException {
            synchronized (monitor1) {
                System.out.println("Esperando en monitor2...");
                monitor1.notifyAll();
                System.out.println("Despertado en monitor2!");
            }
        }

    private final Object monitor2 = new Object();


    public void pedirMonitorResta() throws InterruptedException {
            synchronized (monitor2) {
                System.out.println("Esperando en monitor2...");
                monitor2.wait();
                System.out.println("Despertado en monitor2!");
            }
        }

    public void soltarMonitorResta() throws InterruptedException {
        synchronized (monitor2) {
            System.out.println("Esperando en monitor2...");
            monitor2.notifyAll();
            System.out.println("Despertado en monitor2!");
        }
    }
}


