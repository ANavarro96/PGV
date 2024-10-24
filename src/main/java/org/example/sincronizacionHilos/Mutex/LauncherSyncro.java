package org.example.sincronizacionHilos.Mutex;

import org.example.sincronizacionHilos.EjercicioCuenta.Cuenta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class LauncherSyncro {
    public static void main(String[] args) throws InterruptedException {

        // TODO: ¿Cuál es la salida?
        ArrayList<SyncroJava> listaObjetos = new ArrayList<>();
        ArrayList<Thread> listaHilos = new ArrayList<>();

        for (int i = 0; i < 10; i++) listaObjetos.add(new SyncroJava((int) Math.round( Math.random() )));

        for(SyncroJava s : listaObjetos) listaHilos.add(new Thread(s, "Hilo num " + listaObjetos.indexOf(s)));


        for(Thread t : listaHilos) t.start();



        for(Thread t : listaHilos) t.join();



    }
}
