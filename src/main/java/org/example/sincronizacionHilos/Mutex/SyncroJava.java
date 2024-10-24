package org.example.sincronizacionHilos.Mutex;

public class SyncroJava implements Runnable{
    int orden;

    public SyncroJava(int orden) {
        this.orden = orden;
    }

    public static void funcionPGL(){
        System.out.println("Como me gusta PGL :) soy " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void funcionAED(){
        System.out.println("Como me gusta AED :) soy " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
