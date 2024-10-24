package org.example.sincronizacionHilos.SyncroJava;

public class SyncroJava implements Runnable{
    int orden;

    public SyncroJava(int orden) {
        this.orden = orden;
    }

    public static void meGustaPGL(){
        System.out.println("Me gusta mucho PGL la verdad");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void meGustaAED(){
        System.out.println("Me gusta mucho AED la verdad");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10;i++){
            if(orden == 1) meGustaAED();
            else meGustaPGL();
            orden = 1 - orden;
        }
    }
}
