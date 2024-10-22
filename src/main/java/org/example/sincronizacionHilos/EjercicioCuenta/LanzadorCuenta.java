package org.example.sincronizacionHilos.EjercicioCuenta;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LanzadorCuenta {

    static final int NUM_HILOS = 100;
    public static void main(String[] args) throws InterruptedException {

       // TODO: lanzar 5, 50 y 100 hilos con Cuenta y mostrar el resultado
        Cuenta.max_veces = 100;
        Cuenta c = new Cuenta();
        ArrayList<Thread> listaH = new ArrayList<>();
        // TODO
        for(int i = 0; i < NUM_HILOS;i++){
            listaH.add(new Thread(c));
        }
        for(Thread t :listaH) t.start();
        for(Thread t : listaH) t.join();

        System.out.println(Cuenta.getCuenta());
    }
}
