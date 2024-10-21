package org.example.hilos;

public class LauncherYield {

    public static void main(String[] args) throws InterruptedException {
        // CREAD UNA LISTA de 10 hilos
        PruebaYield p1 = new PruebaYield("Hilo 1");
        PruebaYield p2 = new PruebaYield("Hilo 2");

        Thread hilo1 = new Thread(p1);
        Thread hilo2 = new Thread(p2);

        // asignad a cada hilo una prioridad al azar

        hilo2.start();hilo1.start();

        hilo1.join();hilo2.join();

    }
}
