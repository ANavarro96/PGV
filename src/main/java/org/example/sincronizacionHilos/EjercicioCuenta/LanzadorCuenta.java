package org.example.sincronizacionHilos.EjercicioCuenta;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LanzadorCuenta {

    public static void main(String[] args) throws InterruptedException {

       // TODO: lanzar 5, 50 y 100 hilos con Cuenta y mostrar el resultado
        Cuenta.max_veces = 0;
        Cuenta c = new Cuenta();
        ArrayList<Thread> listaH = new ArrayList<>();
        // TODO

        System.out.println(Cuenta.getCuenta());
    }
}
