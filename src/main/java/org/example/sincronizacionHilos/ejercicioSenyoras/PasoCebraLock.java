package org.example.sincronizacionHilos.ejercicioSenyoras;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PasoCebraLock {

      private boolean haySenyoraPasando = false;
      private int ciclistasPasando = 0;
        private int senyorasEsperando = 0;
        ReentrantLock lock = new ReentrantLock();
        Condition esperando = lock.newCondition();

        public void entrarCiclista(String nombre) throws InterruptedException {
            lock.lock();
            while (haySenyoraPasando || senyorasEsperando > 0) {
               esperando.await(); // Esperar si hay una señora pasando o esperando
            }
            ciclistasPasando++;
            lock.unlock();

        }

        public void salirCiclista(String nombre) throws InterruptedException {
            lock.lock();
            ciclistasPasando--;
            System.out.println(nombre + " ha terminado de pasar.");
            esperando.signalAll();  // Notificar a las señoras y ciclistas que puedan estar esperando
            lock.unlock();
        }

        public void entrarSenyora(String nombre) throws InterruptedException {
            lock.lock();
            while (haySenyoraPasando || ciclistasPasando > 0) {
                esperando.await();  // Esperar si ya hay otra señora pasando
            }
            senyorasEsperando--;
            haySenyoraPasando = true;
            lock.unlock();
        }

        public void salirSenyora(String nombre) throws InterruptedException {
            lock.lock();
            System.out.println(nombre + " ha terminado de pasar.");
            haySenyoraPasando = false;
            esperando.signalAll();  // Notificar a los ciclistas y otras señoras
            lock.unlock();
        }
}
