package org.example.hilos;

// Clase PruebaContar que implementa Runnable
class PruebaContar implements Runnable {
    int cuenta = 0; // Variable que va a incrementar el hilo

    @Override
    public void run() {
        // Bucle que incrementa la variable cuenta hasta 100
        System.out.println("Antes de empezar, cuenta es " + cuenta);
        for (int i = cuenta; cuenta < 100; i++) {
            cuenta++;
            System.out.println(Thread.currentThread().getName() + " - Cuenta: " + cuenta);
            try {
                Thread.sleep(50); // Añadimos un pequeño sleep para observar el incremento
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " fue interrumpido.");
                break; // Salir del bucle si el hilo es interrumpido
            }
        }
    }
}

// Clase principal LauncherPruebaContar con el método main para probar los hilos
public class LauncherPruebaContar {
    public static void main(String[] args) {
        // Crear objeto de la clase PruebaContar
        PruebaContar pc = new PruebaContar();

        // CASO 1: Crear un hilo, lanzarlo, y pararlo con el método stop
        Thread t1 = new Thread(pc, "Hilo-1");
        t1.start();
        try {
            Thread.sleep(500); // Esperar un poco antes de pararlo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.stop(); // Parar el hilo (obsoleto, pero usado para el ejercicio)
        System.out.println("Valor de cuenta tras parar Hilo-1: " + pc.cuenta);

        // Intentar relanzar el hilo después de pararlo (no se puede relanzar un hilo detenido)
        try {
            t1.start(); // Esto lanzará una excepción IllegalThreadStateException
        } catch (IllegalThreadStateException e) {
            System.out.println("No se puede reiniciar un hilo ya detenido.");
        }

        // CASO 2: Crear un hilo, lanzarlo, y detenerlo tras un segundo.
        PruebaContar pc2 = new PruebaContar(); // Reiniciamos el objeto para la nueva prueba
        Thread t2 = new Thread(pc2, "Hilo-2");
        t2.start();
        try {
            Thread.sleep(1000); // Dejar correr el hilo durante un segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.stop(); // Parar el hilo
        System.out.println("Valor de cuenta tras parar Hilo-2: " + pc2.cuenta);

        // Crear un hilo nuevo sobre el mismo objeto pc2
        Thread t3 = new Thread(pc2, "Hilo-3");
        t3.start();
        try {
            Thread.sleep(1000); // Esperar un segundo y parar el hilo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.stop();
        System.out.println("Valor de cuenta tras parar Hilo-3: " + pc2.cuenta);

        // CASO 3: Poner cuenta a 500 antes de lanzar el segundo hilo
        PruebaContar pc3 = new PruebaContar();
        pc3.cuenta = 50; // Establecer cuenta a 50
        Thread t4 = new Thread(pc3, "Hilo-4");
        t4.start();
        try {
            Thread.sleep(1000); // Dejar correr el hilo durante un segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t4.stop(); // Parar el hilo
        System.out.println("Valor de cuenta tras parar Hilo-4: " + pc3.cuenta);

        // CASO 4: Crear dos hilos que se ejecuten sobre el mismo objeto pc3
        Thread t5 = new Thread(pc3, "Hilo-5");
        Thread t6 = new Thread(pc3, "Hilo-6");
        t5.start();
        t6.start(); // Iniciar el segundo hilo sobre el mismo objeto

        try {
            t5.join(); // Esperar a que ambos hilos terminen
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Valor de cuenta tras ejecutar Hilo-5 y Hilo-6 sobre el mismo objeto: " + pc3.cuenta);
    }
}
