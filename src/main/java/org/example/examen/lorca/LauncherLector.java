package org.example.examen.lorca;

import java.io.File;
import java.util.ArrayList;

public class LauncherLector {
    public static void main(String[] args) throws InterruptedException {
        File fLorca = new File("lorca.txt");
        ArrayList<Integer> lista = new ArrayList<>();
        for( int i = 0; i < 5;i++) lista.add(0);

        Lector l1 = new Lector(fLorca, 'a',lista);
        Lector l2 = new Lector(fLorca, 'e',lista);
        Lector l3 = new Lector(fLorca, 'i',lista);
        Lector l4 = new Lector(fLorca, 'o',lista);
        Lector l5 = new Lector(fLorca, 'u',lista);

        ArrayList<Thread> listaH = new ArrayList<>();
        listaH.add(new Thread(l1));
        listaH.add(new Thread(l2));
        listaH.add(new Thread(l3));
        listaH.add(new Thread(l4));
        listaH.add(new Thread(l5));

        listaH.forEach(Thread::start);

        for(Thread t : listaH) t.join();


        System.out.println("El numero total de vocales es " + Lector.totalVocales);
        System.out.println("El numero  de vocales mayusculas es " + Lector.vocalesMayusculas);
    }
}
