package org.example.examen.gatitos;

public class Voluntario implements Runnable{

    String nombre;
    CintaTransporte ct;

    public Voluntario(String nombre, CintaTransporte ct) {
        this.nombre = nombre;
        this.ct = ct;
    }

    @Override
    public void run() {
        while(true){
            Comida nuevo = new Comida(Comida.TIPO.BOLITAS, Comida.TAMANYO.GRANDE);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                ct.prepararComida(nuevo, this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
