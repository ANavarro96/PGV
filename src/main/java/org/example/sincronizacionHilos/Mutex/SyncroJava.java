package org.example.sincronizacionHilos.Mutex;

import java.util.concurrent.locks.ReentrantLock;

public class SyncroJava implements Runnable{
    private static Object mutex1 = new Object();
    private static Object mutex2 = new Object();

    private static ReentrantLock mutexRL = new ReentrantLock();
    int orden;

    public SyncroJava(int orden) {
        this.orden = orden;
    }

    public static void funcionPGL(){
            mutexRL.lock();
            System.out.println("Como me gusta PGL :) soy " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                mutexRL.unlock();
            }

    }

    public static void funcionAED(){
        synchronized (mutex2){
            // boqueo mutex2
            System.out.println("Como me gusta AED :) soy " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // desbloquar


    }

    @Override
    public void run() {
        while(true){
            if(orden == 0) funcionPGL();
            else funcionAED();
            orden = 1 - orden;

        }
    }
}
