package org.example.hilos;

public class pruebaParar {
    static class PruebaContar implements Runnable{

        int cuenta = 0;
        @Override
        public void run() {
            while(cuenta != 100) {
                cuenta++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        PruebaContar hilo = new PruebaContar();

        Thread t = new Thread(hilo);
        t.start();
        System.out.println(t.getState().toString());

        t.stop();

        System.out.println(t.getState().toString());

        System.out.println(hilo.cuenta);

        //t.start();

        Thread j = new Thread(hilo);
        j.start();
        
        System.out.println(t.getState().toString());

        j.stop();

        System.out.println(t.getState().toString());

        System.out.println(hilo.cuenta);

    }
}
