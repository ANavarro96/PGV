package org.example.hilos;

public class PruebaContar implements Runnable{
    int cuenta;

    public PruebaContar(int cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        while(cuenta != 100){
            cuenta++;
            Thread.yield();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Llevo un total de " + cuenta );
        }
        cuenta = 0;
    }
}
