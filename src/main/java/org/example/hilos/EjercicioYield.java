package org.example.hilos;

public class EjercicioYield implements Runnable {
    private String threadName;

    public EjercicioYield(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " - Count: " + i);
            // Llamada a yield() para sugerir al scheduler que ceda el control.
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        // Crear dos hilos y lanzar con yield()
        System.out.println("Ejemplo con 2 hilos y yield():");
        Thread t1 = new Thread(new EjercicioYield("Thread 1"));
        Thread t2 = new Thread(new EjercicioYield("Thread 2"));

        t1.start();
        t2.start();

        // Esperar a que los dos hilos terminen antes de pasar a las otras pruebas
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Quitar yield() y lanzar los hilos
        System.out.println("\nEjemplo con 2 hilos sin yield():");
        t1 = new Thread(new EjercicioYield("Thread 1"));
        t2 = new Thread(new EjercicioYield("Thread 2"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Añadir 5 hilos con yield()
        System.out.println("\nEjemplo con 5 hilos y yield():");
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new EjercicioYield("Thread " + (i + 1)));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Modificar las prioridades de los hilos
/*
Con yield(): Los hilos tienden a turnarse más, pero no siempre es el caso, ya que el método es solo una sugerencia para el planificador.
Sin yield(): Un hilo puede ejecutar todas sus iteraciones seguidas antes de ceder el control a otro.
Con más hilos: Aumenta la competencia por la CPU, lo que puede hacer que los hilos se ejecuten de manera menos predecible.
Con diferentes prioridades: Los hilos con mayor prioridad tienden a ejecutarse con más frecuencia.
 */

    }
}
