package org.example.sincronizacionHilos;

class EjemploVolatile {
    private volatile boolean flag = false;

    public void cambiarFlag() {
        flag = true;  // El cambio será INMEDIANTE visible para otros hilos
    }

    public void revisarFlag() {
        while (!flag) {
            // Nos quedamos aquí
        }
    }
}
