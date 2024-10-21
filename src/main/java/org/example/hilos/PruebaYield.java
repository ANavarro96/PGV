package org.example.hilos;

public class PruebaYield implements Runnable{
    String nombre;

    public PruebaYield(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for(int i = 0;i < 5;i++){
            System.out.println("Soy " + nombre + " y llevo " + i +
                    "Mi pioridad es " + Thread.currentThread().getPriority());
             //Thread.yield(); NO USAR >:(
        }
    }
}
