package org.example.sincronizacionHilos.multiMonitor;

public interface Monitor {

    void pedirMonitorSuma() throws InterruptedException;
    void soltarMonitorSuma() throws InterruptedException;
    void pedirMonitorResta() throws InterruptedException;
    void soltarMonitorResta() throws InterruptedException;

}
