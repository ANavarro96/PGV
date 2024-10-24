package org.example.sincronizacionHilos.Mutex;

import org.example.sincronizacionHilos.EjercicioCuenta.Cuenta;

import java.util.ArrayList;

public class LauncherSyncro {
    public static void main(String[] args) {

        // TODO: lanzar 5, 50 y 100 hilos con Cuenta y mostrar el resultado
        Cuenta.max_veces = 0;
        Cuenta c = new Cuenta();
        ArrayList<Thread> listaH = new ArrayList<>();
        // TODO
        for(int i = 0; i < 100; i++) listaH.add(new Thread(c));
        listaH.forEach(Thread::start);
        listaH.forEach(thread -> {
            try {
                thread.join(); // Join the thread and wait for its completion
            } catch (InterruptedException e) {
                // Handle the exception, for example, by printing the stack trace or logging
                e.printStackTrace();
            }
        });

        System.out.println(Cuenta.getCuenta());

    }
}
