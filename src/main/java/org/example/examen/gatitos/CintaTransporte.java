package org.example.examen.gatitos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CintaTransporte {
    final int MAXIMO = 3;
    Queue<Comida> cinta = new LinkedList<>();
    static ReentrantLock monitor = new ReentrantLock();
    static Condition condicionGatito = monitor.newCondition();
    static int gatitosHamrientos = 0;

    public boolean comer(Gatito g) throws InterruptedException {
        monitor.lock();

        while (cinta.isEmpty()){
            condicionGatito.await(); // BLOCK
        }
        Comida bol = cinta.peek();
        boolean heComido = false;
        if(bol.tipoComida == g.favorita){
            cinta.poll(); // quito el elemento de la lista
            System.out.println("El gatito " + g.nombre + " come");
            Thread.sleep(5000);
            heComido = true;
        }
        else{
            System.out.println("El gatito " + g.nombre + " dic que Este bol es un asco :( miau miau");
            Thread.sleep(5000);
        }
        condicionGatito.signalAll();
        monitor.unlock();
        return heComido;
    }

    public void prepararComida(Comida nuevoBol, Voluntario v) throws InterruptedException {
        monitor.lock();
        while(cinta.size() == MAXIMO || gatitosHamrientos == 0){
            condicionGatito.await();
        }
        System.out.println("Soy el voluntario " + v.nombre + " y pongo comida");
        Thread.sleep(5000);
        cinta.add(nuevoBol);
        condicionGatito.signalAll();
        monitor.unlock();
    }



}
