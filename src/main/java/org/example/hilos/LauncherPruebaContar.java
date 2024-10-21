package org.example.hilos;

public class LauncherPruebaContar {

    public static void main(String[] args) throws InterruptedException {
        PruebaContar pc = new PruebaContar(90); // main
        PruebaContar pc1 = new PruebaContar(50); // main

        Thread t = new Thread(pc);
        Thread t1 = new Thread(pc1);

        t.start();
        //t1.start();

        //t1.join();
        t.join();

        //t.start();
        // NO PODEMOS LAZAR UN OBJETO CUANDO SE TERMINE
        t = new Thread(pc);
        t.start();

        t.join();





    }


}
