package org.example.hilos;

import java.util.ArrayList;
import java.util.Random;

public class LauncherHilo {
    public static void main(String[] args) throws InterruptedException {

        // creamos una lista de objetos
         ArrayList<JugadorHilo> listaMataos = new ArrayList<>();

        for(int i = 0; i < 10;i++){
            listaMataos.add(new JugadorHilo(new Random().nextInt(10000) + 1,"Jugador hilo" + i));
        }

        // declaramos una lista de hilos.
        ArrayList<Thread> listaHilos = new ArrayList<>();

        // los añadimos  a la lista (no se han lanzado todavía)
        for (JugadorHilo js : listaMataos ) {
            listaHilos.add(new Thread(js));
        }
        long start = System.currentTimeMillis();
        //lanzarlos con start()
        for(Thread t : listaHilos) t.start(); // ProcessBuilder.start()


        // pararlos join
        for(Thread t : listaHilos) t.join(); // process.waitFor()
        //System.out.println("Un total de " + listaMataos.stream().map(hilos.JugadorHilo::getTiempo).reduce(0, Integer::sum)/1000);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Un total de " + timeElapsed / 1000);

    }

}
