package org.example.examen.gatitos;

import java.util.ArrayList;
import java.util.Random;

public class LauncherGatitos {


    public static void main(String[] args) {
      CintaTransporte ct = new CintaTransporte();
      ArrayList<Gatito> listaG = new ArrayList<>();
      ArrayList<Voluntario> listaV = new ArrayList<>();

      for(int i = 0; i < 10;i++) listaG.add(
              new Gatito("Gatito " + i, favoritoAzar(), ct));

      for(int i = 0; i < 6;i++) listaV.add(new Voluntario("Voluntario " + i, ct));

      ArrayList<Thread> listaHilosG = new ArrayList<>();

      listaG.forEach(g -> listaHilosG.add(new Thread(g)));

      ArrayList<Thread> listaHilosV = new ArrayList<>();
        listaV.forEach(v -> listaHilosV.add(new Thread(v)));

        listaHilosG.forEach(Thread::start);
        listaHilosV.forEach(Thread::start);



    }

    public static Comida.TIPO favoritoAzar(){
        Random r = new Random();
        switch(r.nextInt(3)){
            case 0 -> {
                return Comida.TIPO.BOLITAS;
            }
            case 1 -> {
                return Comida.TIPO.MOUSSE;
            }
            default -> {
                return Comida.TIPO.PATE;
            }
        }
    }
}
