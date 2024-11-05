package org.example.sincronizacionHilos.semaforo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CanchaLock {
    // bloqueo con un ReentrantLock
    static final int MAX_BALONES = 10;
    static int numBalones = 10;
    static int numCanastas = 4;

    static ReentrantLock monitorEntrada = new ReentrantLock();
    Condition listaEspera = monitorEntrada.newCondition(); // WAITING LIST

    public void coger(int balones, int canastas) throws InterruptedException {
      if(balones <= MAX_BALONES){
          monitorEntrada.lock(); // synchronized
          while(balones < numBalones || canastas < numCanastas){
              listaEspera.await(); // ME BLOQUEO wait(()
          }
          try{
              numBalones -= balones;
              numCanastas -= canastas;
          }finally {
              monitorEntrada.unlock();
          }
      }

    }


    public void dejar(int balones, int canastas){
        monitorEntrada.lock(); // synchronized
        try {
            numBalones += balones;
            numCanastas += canastas;
            listaEspera.signalAll(); // notifyAll()
        }
        finally {
            monitorEntrada.unlock(); // synchronized
        }

    }

}
