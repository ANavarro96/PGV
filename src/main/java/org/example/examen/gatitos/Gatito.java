package org.example.examen.gatitos;

import java.util.concurrent.Semaphore;

public class Gatito implements Runnable{

    String nombre;
    Comida.TIPO favorita;
    CintaTransporte cinta;
    Semaphore s = new Semaphore(1);

    public Gatito(String nombre, Comida.TIPO favorita, CintaTransporte cinta) {
        this.nombre = nombre;
        this.favorita = favorita;
        this.cinta = cinta;
    }

    @Override
    public void run() {
        int bolesComidos = 0;
        try {
            s.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CintaTransporte.gatitosHamrientos++;
        s.release();
        while(bolesComidos < 3){
            try {
                if(cinta.comer(this)){
                    bolesComidos++;
                    System.out.println("AQui digiriendo" );
                    try {
                        Thread.sleep(4999);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(4999);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



        }
        try {
            s.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CintaTransporte.gatitosHamrientos--;
        s.release();

    }
}
