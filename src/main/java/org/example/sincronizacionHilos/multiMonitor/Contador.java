package org.example.sincronizacionHilos.multiMonitor;

import java.util.Random;

public class Contador implements Runnable{

    Monitor monitores;
    public static int suma = 0;
    public static int resta = 0;

    int orden = new Random().nextInt(0,2);

    public Contador(Monitor monitor){
        monitores = monitor;
    }

    @Override
    public void run() {
        while(true){
            try {
                if (orden == 0) {
                    monitores.pedirMonitorSuma();
                    suma++;
                    monitores.soltarMonitorSuma();
                } else {
                    monitores.pedirMonitorResta();
                    resta--;
                    monitores.soltarMonitorResta();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
