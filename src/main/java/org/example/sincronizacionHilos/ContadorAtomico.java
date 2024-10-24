package org.example.sincronizacionHilos;
import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAtomico {
    private AtomicInteger cuenta = new AtomicInteger(0);

    public void incrementar() {
        cuenta.incrementAndGet();  // Esto es como cuenta++, pero atómico
    }

    public int obtenerCuenta() {
        return cuenta.get();  // Leemos de forma atómica
    }
}
