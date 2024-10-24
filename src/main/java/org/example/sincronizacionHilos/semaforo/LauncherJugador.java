package org.example.sincronizacionHilos.semaforo;

import org.example.sincronizacionHilos.Mutex.SyncroJava;

import java.util.ArrayList;

public class LauncherJugador {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Jugador> listaObjetos = new ArrayList<>();
        ArrayList<Thread> listaHilos = new ArrayList<>();

        for (int i = 0; i < 10; i++) listaObjetos.add(new Jugador());

        for(Jugador s : listaObjetos) listaHilos.add(new Thread(s, "Hilo num " + listaObjetos.indexOf(s)));


        for(Thread t : listaHilos) t.start();



        for(Thread t : listaHilos) t.join();
    }
}
