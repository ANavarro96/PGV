package org.example.sincronizacionHilos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Banco {
    private int id;
    private double dinerito;
    private Lock monitorBanco = new ReentrantLock();

    public Banco(int id, double dinerito) {
        this.id = id;
        this.dinerito = dinerito;
    }

    public void meter(double cuanto) {
        dinerito += cuanto;
    }

    public void sacar(double cuanto) {
        dinerito -= cuanto;
    }

    public void hacerTransferencia(Banco destinatario, double amount) {
        if (monitorBanco.tryLock()) {
            try {
                System.out.println("Bloqueo el banco " + id);
                if (destinatario.monitorBanco.tryLock()) {
                    try {
                        if (dinerito >= amount) {
                            this.sacar(amount);
                            destinatario.meter(amount);
                            System.out.println("He hecho la transferencia de " + amount + " de " + id
                                    + " a  " + destinatario.id);
                        }
                    } finally {
                        destinatario.monitorBanco.unlock();
                    }
                } else {
                    System.out.println("No se puede transferir el banco " + destinatario.id + " está ocupado haciendo otras cosas");
                }
            } finally {
                monitorBanco.unlock();
            }

        } else {
            System.out.println("El banco " + id + " está bloqueado, se estarán haciendo otras cosas");
        }
    }
}

public class EjemploBanco {
    public static void main(String[] args) {
        Banco bancoA = new Banco(1, 500);
        Banco bancoB = new Banco(2, 500);

        Thread thread1 = new Thread(() -> bancoA.hacerTransferencia(bancoB, 100));
        Thread thread2 = new Thread(() -> bancoB.hacerTransferencia(bancoA, 100));

        thread1.start();
        thread2.start();
    }
}
