package org.example.sincronizacionHilos.productorConsumidor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ListaCompartida {

    private final Queue<Integer> colica = new LinkedList<>();

    public ListaCompartida() {
    }

    ReentrantLock lock = new ReentrantLock();
    Condition condicionMeter = lock.newCondition();
    Condition condicionSacar = lock.newCondition();

    public  void escribir(int n) {
            try {
                lock.lock();
                // acquire
                while (colica.size() == 5) {
                    condicionMeter.await();
                }
                colica.add(n);
                Thread.sleep(3000);
                // release
                condicionSacar.signalAll(); // avisamos a todos los hilos
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                lock.unlock();
            }
        }

    public  int leer(){

        try {
            lock.lock();
            while(colica.isEmpty()) {
                condicionSacar.await();
            }
            int leido = colica.poll();
            Thread.sleep(3000);
            condicionMeter.signalAll();
            return leido;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }


    }

    }


