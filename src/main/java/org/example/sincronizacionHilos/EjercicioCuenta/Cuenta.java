package org.example.sincronizacionHilos.EjercicioCuenta;

public class Cuenta implements Runnable{
    private static int cuenta;
    public static int max_veces;

    public static int getCuenta() {
        return cuenta;
    }

    public void incrementar() { cuenta++;}
    @Override
    public void run() {
        for(int i = 0; i < max_veces; i++) incrementar();
    }
}
